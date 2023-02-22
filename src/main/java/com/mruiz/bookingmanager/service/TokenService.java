package com.mruiz.bookingmanager.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.mruiz.bookingmanager.entity.User;
import com.mruiz.bookingmanager.repository.UserRepository;

@Service
public class TokenService {
	
	@Autowired
	private JwtEncoder encoder;
	
	@Autowired
	private JwtDecoder decoder;
	
	@Autowired
	private UserRepository userRepository;

	public String generateToken(Authentication authentication) {
		Instant now = Instant.now();
		String scope = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));
		JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now)
				.expiresAt(now.plus(1, ChronoUnit.HOURS)).subject(authentication.getName()).claim("scope", scope)
				.build();
		return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
	
	public String getUsernameFromToken(String token) {
		return decoder.decode(token).getSubject();
	}
	
	public User getUserFromAuthorities(Authentication auth) {
		try {
			String token = ((Jwt)auth.getCredentials()).getTokenValue();
			return userRepository.findByUsername(getUsernameFromToken(token)).get();
		} catch (NullPointerException ex) {
			// Si en vez de usar el bearer token se inicia con user/password
			// se podra hacer aun asi
			return userRepository.findByUsername(auth.getName()).get();
		}
	}
}
