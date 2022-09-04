package com.stackroute.userservice.service;

import java.util.Map;

import com.stackroute.userservice.domain.User;

public interface TokenGeneratorService {

	public Map<String, String> generateToken(User user);
}
