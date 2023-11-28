package com.learntoday.model;

import lombok.Data;

@Data
public class AuthenticationResponse {
	private String username;
	private String message;
	private String jwt;
}
