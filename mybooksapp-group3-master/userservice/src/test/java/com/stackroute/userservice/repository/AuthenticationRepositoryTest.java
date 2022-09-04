
package com.stackroute.userservice.repository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userservice.domain.User;




@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AuthenticationRepositoryTest {

	@Autowired
	private AuthenticationRepository userRepository;

	private User user;

	@Before
	public void  setUp() throws Exception {
		user = new User("Minato", "Naruto", "Uzumaki", "YellowFlash");
	}

	
	@Test
	public void testSave() throws Exception {
		userRepository.save(user);
		assertTrue(userRepository.existsById("Minato"));
		userRepository.delete(user);
	}
	
	@Test
	public void testDeleteuser() {
		userRepository.save(user);
		assertTrue(userRepository.existsById("Minato"));
		userRepository.delete(user);
		assertFalse(userRepository.existsById("Minato"));
		
	}
	
	@Test
	public void getGetAUserById() {
		userRepository.save(user); 
		Optional<User> user2 =userRepository.findById(user.getUserId());
		assertThat(user2.get().equals(user));
		userRepository.delete(user);
	}
	
	@Test
	public void testList() {
		userRepository.save(user); 
		
		List<User> userList = userRepository.findAll();
		assertNotNull(userList);
	}
	
}
