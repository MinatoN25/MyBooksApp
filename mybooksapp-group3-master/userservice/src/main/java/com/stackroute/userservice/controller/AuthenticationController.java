package com.stackroute.userservice.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistException;
import com.stackroute.userservice.exception.UserNotAvailableException;
import com.stackroute.userservice.repository.AuthenticationRepository;
import com.stackroute.userservice.service.AuthenticationService;
import com.stackroute.userservice.service.TokenGeneratorService;
import com.stackroute.userservice.util.AuthenticationConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@CrossOrigin(origins="*")
@RequestMapping(AuthenticationConstants.ROOT_PATH)
public class AuthenticationController {


	private final AuthenticationService authService;	
	private final TokenGeneratorService  tokenGenerator;	

	public AuthenticationController(AuthenticationService authService, TokenGeneratorService  tokenGenerator) {
		this.tokenGenerator=tokenGenerator;
		this.authService=authService;		
	}

	@ApiOperation(value="registerUser", notes="Register user for My Favourite Books App")
	@PostMapping(path = AuthenticationConstants.REGISTER_PATH) 
	public ResponseEntity<?>  registerUser(@ApiParam(name="User", value="User", required=true) @RequestBody User user){
		ResponseEntity<String> reponse;
		try {
			if(null == user.getFirstName() || user.getFirstName().isEmpty() ) {
				return new ResponseEntity<String>(AuthenticationConstants.FIRST_NAME_MSG,HttpStatus.BAD_REQUEST);
			}
			if(null == user.getLastName() || user.getLastName().isEmpty()) {
				return new ResponseEntity<String>(AuthenticationConstants.LAST_NAME_MSG,HttpStatus.BAD_REQUEST);
			}
			if(null == user.getUserId() || user.getUserId().isEmpty() ||
					null == user.getPassword() || user.getPassword().isEmpty()	) {
				return new ResponseEntity<String>(AuthenticationConstants.USERID_MSG,HttpStatus.BAD_REQUEST);
			}

			reponse = new ResponseEntity<>(authService.saveUser(user),HttpStatus.CREATED);		
		}catch(UserAlreadyExistException e) {
			return new ResponseEntity<String>(e.getErrorMessage(),HttpStatus.CONFLICT);
		}
		return reponse;
	}

	@ApiOperation(value="login", notes="Login user for My Favourite Books App")
	@PostMapping(path = AuthenticationConstants.LOGIN_PATH) 
	public ResponseEntity<?>  loginUser(@ApiParam(name="User", value="User", required=true) @RequestBody User user){
		ResponseEntity<String> reponse;
		try {

			if(null == user.getUserId() || user.getUserId().isEmpty() ||
					null == user.getPassword() || user.getPassword().isEmpty()	) {
				return new ResponseEntity<String>(AuthenticationConstants.USERID_MSG,HttpStatus.UNAUTHORIZED);
			}

			User loggedInUser = authService.getUserDetails(user.getUserId(), user.getPassword());

			Map<String, String> map = tokenGenerator.generateToken(loggedInUser);

			reponse = new ResponseEntity<>(map.get("token"),HttpStatus.OK);		

		}catch(UserNotAvailableException e) {
			return new ResponseEntity<String>(e.getErrorMessage(),HttpStatus.UNAUTHORIZED);
		}
		return reponse;
	}

//Added by manish	
	@ApiOperation(value="my-profile", notes="user profile page")
	@PutMapping(path = AuthenticationConstants.PROFILE_PATH) 
	public ResponseEntity<?>  UserProfile(@ApiParam(name="User", value="User", required=true) @RequestBody User user){
		ResponseEntity<String> reponse;
		try {
			if(null == user.getFirstName() || user.getFirstName().isEmpty() ) {
				return new ResponseEntity<String>(AuthenticationConstants.FIRST_NAME_MSG,HttpStatus.BAD_REQUEST);
			}
			if(null == user.getLastName() || user.getLastName().isEmpty()) {
				return new ResponseEntity<String>(AuthenticationConstants.LAST_NAME_MSG,HttpStatus.BAD_REQUEST);
			}
			if(null == user.getUserId() || user.getUserId().isEmpty() ||
					null == user.getPassword() || user.getPassword().isEmpty()	) {
				return new ResponseEntity<String>(AuthenticationConstants.USERID_MSG,HttpStatus.BAD_REQUEST);
			}
			reponse = new ResponseEntity<>(authService.updateUser(user),HttpStatus.CREATED);		
		}catch(UserNotAvailableException e) {
			return new ResponseEntity<String>(e.getErrorMessage(),HttpStatus.CONFLICT);
		}
		return reponse;
	}
	
	@ApiOperation(value="getUser", notes="user profile page")
	@GetMapping(path = AuthenticationConstants.LOGIN_PATH) 
	public ResponseEntity<?>  getUserData(){
		ResponseEntity<User> reponse;
		User user = new User();
			reponse = new ResponseEntity<User>(authService.getUserDetails(user.getUserId(), user.getPassword()),HttpStatus.OK);		
		
		return reponse;
	}

}
