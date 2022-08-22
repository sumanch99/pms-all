package com.cognizant.pms.login.apiRequest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationRequestTest {
	
	@Test
	public void testAuthenticationRequest() {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setPassword("abc");
		authenticationRequest.setUserName("abc");
		assertEquals("abc", authenticationRequest.getUserName());
		assertEquals("abc", authenticationRequest.getPassword());
		AuthenticationRequest a = AuthenticationRequest.builder().build();
		assertNotNull(a);
		a.toString();
		AuthenticationRequest authenticationRequestBuilder = AuthenticationRequest.builder().userName("abc").password("abc").build();
		AuthenticationRequest.builder().toString();
		assertNotNull(authenticationRequestBuilder);
	}

}