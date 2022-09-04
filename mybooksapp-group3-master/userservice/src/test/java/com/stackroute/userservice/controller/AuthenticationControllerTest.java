
package com.stackroute.userservice.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.service.AuthenticationServiceImpl;
import com.stackroute.userservice.service.TokenGeneratorService;

@RunWith(SpringRunner.class)

@WebMvcTest(AuthenticationController.class)
public class AuthenticationControllerTest {
	@Autowired
	private  MockMvc mockMvc;

	@MockBean
	private   AuthenticationServiceImpl userServiceImpl;

	@MockBean
	private TokenGeneratorService TokenGenerator;
	
	private  User user;
	private  User user1;
	@InjectMocks
	private AuthenticationController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("Kirito", "Kazuto", "kirigaya", "BlueRose");
		user1 = new User("","db","naruto","someone");
	}

	@Test
	public void testRegisterUserSuccess() throws Exception {
		when(userServiceImpl.saveUser(user)).thenReturn("User successfully registered to the App");
		mockMvc.perform(post("/api/books/registerUser").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(status()
						.isCreated()).andDo(print());
		verify(userServiceImpl, times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userServiceImpl);
	}
	
	@Test
	public void testRegisterFailure() throws Exception {
		when(userServiceImpl.saveUser(user1)).thenReturn("User id already exists");
		mockMvc.perform(post("/api/books/registerUser").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user1))).andExpect(status()
						.isBadRequest()).andDo(print());
	}

	@Test
	public void testLoginUserSuccess() throws Exception {
		when(userServiceImpl.getUserDetails(user.getUserId(),user.getPassword())).thenReturn(user);

		mockMvc.perform(post("/api/books/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(status().isOk());

		verify(userServiceImpl, times(1)).getUserDetails(user.getUserId(), user.getPassword());
		verifyNoMoreInteractions(userServiceImpl);
	}
	@Test
	public void testLoginUserFailure() throws Exception {
		mockMvc.perform(post("/api/books/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user1))).andExpect(status().isUnauthorized());

	}
	@Test
	public void testUpdateUserSuccess() throws Exception {
		when(userServiceImpl.updateUser(user)).thenReturn("User successfully updated");
		mockMvc.perform(put("/api/books/my-profile").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(status()
						.isCreated());
		verify(userServiceImpl, times(1)).updateUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userServiceImpl);
	}
	@Test
	public void testUpdateUserFailure() throws Exception {
		when(userServiceImpl.updateUser(user)).thenReturn("enter userId properly");
		mockMvc.perform(put("/api/books/my-profile").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user1))).andExpect(status()
						.isBadRequest());
	}
	
	public static String jsonToString(final Object obj) {
		try {

			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
