package com.cognizant.pms.login.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationStatusTest {
	
	@Test
	public void testAuthenticationStatus() {
		AuthenticationStatus authenticationStatus = new AuthenticationStatus(true, "Authenticated");
		assertNotNull(authenticationStatus);
		authenticationStatus.setIsAuthenticated(false);
		authenticationStatus.setMessage("Unauthenticated");
		assertEquals(false, authenticationStatus.getIsAuthenticated());
		assertEquals("Unauthenticated", authenticationStatus.getMessage());

	}

}