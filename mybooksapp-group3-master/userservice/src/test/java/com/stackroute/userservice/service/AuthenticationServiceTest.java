
package com.stackroute.userservice.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotEquals;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.userservice.exception.UserAlreadyExistException;
import com.stackroute.userservice.exception.UserNotAvailableException;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.repository.AuthenticationRepository;

@RunWith(SpringRunner.class)
public class AuthenticationServiceTest {
	@Mock
	private AuthenticationRepository userRepository;

	private User user;
	private User user1;
	@InjectMocks
	private AuthenticationServiceImpl service;

	private Optional<User> options;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("SlugQueen", "Tsunade", "Senju", "Sannin");
		user1 = new User("a", "Tsunade", "Senju", "a");
		options = Optional.of(user);
	}

	@Test
	public void testSaveUserSuccess() throws UserAlreadyExistException {
		when(userRepository.save(user)).thenReturn(user);
		String msg = service.saveUser(user);
		assertEquals("User successfully registered to the App", msg);
		verify(userRepository, times(1)).save(user);
	}

	@Test(expected = UserAlreadyExistException.class)
	public void testSaveUserFailure() throws UserAlreadyExistException {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		when(userRepository.save(user)).thenReturn(user);
		service.saveUser(user);
		verify(userRepository, times(1)).findById(user.getUserId());
	}

	@Test
	public void testUpdateUserSuccess() {
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		when(userRepository.save(user)).thenReturn(user);
		String msg = service.updateUser(new User("SlugQueen", "updated", "Senju", "Sannin"));
		assertEquals("User successfully updated", msg);
		verify(userRepository, times(1)).findById(user.getUserId());
	}

	@Test(expected = UserNotAvailableException.class)
	public void testUpdateUserFailure() {
		service.updateUser(user1);
		verify(userRepository, times(1)).findById(user.getUserId());
	}

	@Test
	public void testGetUserSuccess() throws UserNotAvailableException {
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult = service.getUserDetails(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals(user.getUserId(), userResult.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}

	@Test(expected = UserNotAvailableException.class)
	public void testGetUserFailure() throws UserNotAvailableException {
		when(userRepository.findByUserIdAndPassword(user1.getUserId(), user1.getPassword())).thenReturn(null);
		User userResult = service.getUserDetails(user1.getUserId(), user1.getPassword());
		assertNotEquals(user.getUserId(), userResult.getUserId());
	}

}
