package com.cognizant.pms.login.apiResponse;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationResponseTest {
	
	@Test
	public void testAuthenticationResponse() {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		authenticationResponse.setId(0);
		authenticationResponse.setToken("hfvyhvjv");;
		authenticationResponse.setUserName("abc");;
		assertEquals(0, authenticationResponse.getId());
		assertEquals("abc", authenticationResponse.getUserName());
		assertEquals("hfvyhvjv", authenticationResponse.getToken());
		AuthenticationResponse a = AuthenticationResponse.builder().build();
		assertNotNull(a);
		a.toString();
		AuthenticationResponse apiResponseBuilder = AuthenticationResponse.builder().id(0).userName("abc").token("rdyrdyrd").build();
		AuthenticationResponse.builder().toString();
		assertNotNull(apiResponseBuilder);
	}

}