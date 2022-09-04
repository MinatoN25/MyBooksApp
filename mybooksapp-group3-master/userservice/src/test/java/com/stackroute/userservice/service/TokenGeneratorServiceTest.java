package com.stackroute.userservice.service;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userservice.domain.User;

@RunWith(SpringRunner.class)
public class TokenGeneratorServiceTest {
	@Autowired
	TokenGeneratorServiceImpl tokenGenerator;
	
	User user;
	
	@TestConfiguration
	static class testConfig {
		@Bean
		public TokenGeneratorServiceImpl getTokenService() {
			return new TokenGeneratorServiceImpl();
			
		}
	}
	
	@Before
	public void init() {
		user=new User();
		user.setUserId("test");
		user.setFirstName("test");
		user.setLastName("name");
		user.setPassword("test123");
	}
	
	@Test
	public void whenGenerateToken_ThenReturnAuthToken() {
		Map<String, String> map = tokenGenerator.generateToken(user);
		assertEquals("User successfully logged in",map.get("message"));
	}
}
