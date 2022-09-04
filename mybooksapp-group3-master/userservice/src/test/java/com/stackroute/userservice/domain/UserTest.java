package com.stackroute.userservice.domain;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;
public class UserTest {
	private User user;

	@Before
	public void setUp() throws Exception {

		user = new User();
		user.setUserId("Jhon123");
		user.setFirstName("Jhon Simon");
		user.setPassword("123456");
		user.setLastName("test");

	}

	@Test
	public void test() {
		new BeanTester().testBean(User.class);
	}
}
