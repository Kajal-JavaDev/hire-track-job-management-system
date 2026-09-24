package com.example.demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {
	@Value("${JWT_SECRET}")private String secretKey;
	public String generateToken(String username) {
		SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
		
		return Jwts.builder().subject(username).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + 1000*60*60)).signWith(key).compact();
		
	}
	public String extractUsername(String token) {
		SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
		
		return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
	}

}
