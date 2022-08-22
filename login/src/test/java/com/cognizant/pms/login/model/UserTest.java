package com.cognizant.pms.login.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Test
	public void testUser() {
		User user = new User();
		user.setId(0);
		user.setUserName("user");
		user.setPassword("password");
		assertEquals(0, user.getId());
		assertEquals("user", user.getUserName());
		assertEquals("password", user.getPassword());
		User u = User.builder().build();
		assertNotNull(u);
		u.toString();
		User userBuilder = User.builder().id(0).userName("user").password("password").build();
		User.builder().toString();
		assertNotNull(userBuilder);

	}

}