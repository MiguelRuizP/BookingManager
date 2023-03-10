package com.mruiz.bookingmanager.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Nonnull
	@Column(length = 25)
	private String username;
	
	@Nonnull
	@Column(length = 25)
	private String email;
	
	@Nonnull
	@Column(length = 60)
	private String password;
	
}
