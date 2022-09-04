package com.stackroute.userservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.userservice.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenGeneratorServiceImpl implements TokenGeneratorService{

	@Override
	public Map<String, String> generateToken(User user) {
		String jsonToken = "";
		jsonToken = Jwts.builder().setSubject(user.getUserId())
				.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "bookApp").compact();
		Map<String, String> map = new HashMap<>();
		map.put("token", jsonToken);
		map.put("message", "User successfully logged in");
		return map;	
	}
}
