package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;

public interface AuthenticationService {
	public String saveUser(User user);
	public String updateUser(User user);
	public User getUserDetails(String userId, String password);
}
