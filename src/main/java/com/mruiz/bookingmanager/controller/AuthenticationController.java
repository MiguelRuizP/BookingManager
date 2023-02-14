package com.mruiz.bookingmanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mruiz.bookingmanager.entity.User;
import com.mruiz.bookingmanager.payload.FullUserDto;
import com.mruiz.bookingmanager.payload.SimpleMessageDto;
import com.mruiz.bookingmanager.repository.UserRepository;
import com.mruiz.bookingmanager.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private final TokenService tokenService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;

    public AuthenticationController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        return token;
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody FullUserDto userDto){
    	if(userRepository.existsByUsername(userDto.getUsername())) {
    		return ResponseEntity.badRequest().body(new SimpleMessageDto("Usuario ya existe"));
    	}
    	if(userRepository.existsByEmail(userDto.getEmail())) {
    		return ResponseEntity.badRequest().body(new SimpleMessageDto("Correo ya en uso"));
    	}
    	
    	User user = User.builder()
    			.username(userDto.getUsername())
    			.email(userDto.getEmail())
    			.password(encoder.encode(userDto.getPassword()))
    			.build();
    	
    	userRepository.save(user);
    	
    	return ResponseEntity.ok()
    			.body(new SimpleMessageDto(userDto.getUsername() + " registrado con exito!!!"));
    }
}
