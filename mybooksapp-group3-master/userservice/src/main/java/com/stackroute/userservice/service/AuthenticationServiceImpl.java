package com.stackroute.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistException;
import com.stackroute.userservice.exception.UserNotAvailableException;
import com.stackroute.userservice.repository.AuthenticationRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	AuthenticationRepository authRepo;
	
	public String saveUser(User user) {
		Optional<User> currentUser= authRepo.findById(user.getUserId());
		if(currentUser.isPresent()) throw new UserAlreadyExistException("User id already exists");
		authRepo.save(user);
		return "User successfully registered to the App";
	}
	public String updateUser(User user) {
		Optional<User> currentUser= authRepo.findById(user.getUserId());
		if(currentUser.isPresent()) {
		authRepo.save(user);
		}else {
			throw new UserNotAvailableException("enter userId properly");
		}
		return "User successfully updated";
	}
	public User getUserDetails(String userId, String password) {
		User user =authRepo.findByUserIdAndPassword(userId, password);
		if(null == user) throw new UserNotAvailableException("User not found ");
		return user;
	}
}
