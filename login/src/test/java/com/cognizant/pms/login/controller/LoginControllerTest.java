package com.cognizant.pms.login.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.pms.login.apiRequest.AuthenticationRequest;
import com.cognizant.pms.login.constant.CommonTestConstants;
import com.cognizant.pms.login.exception.LoginException;
import com.cognizant.pms.login.service.UserService;
import com.cognizant.pms.login.util.JwtUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {
	
	@Autowired
	private LoginController loginController;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private JwtUtil jwtUtil;
	
	
	@Test
	public void validSignUpTest() {
		AuthenticationRequest authenticationRequest=new AuthenticationRequest();
		assertEquals(200,loginController.signUp(authenticationRequest).getBody().getStatus());
	}
	
	@Test
	public void invalidSignUpTest() throws LoginException {
		when(userService.createNewAccount(null)).thenThrow(RuntimeException.class);
		assertEquals(409,loginController.signUp(null).getBody().getStatus());
	}
	
	
	@Test
	public void createAuthenticationTokenTest() throws LoginException {
		AuthenticationRequest authenticationRequest=new AuthenticationRequest("sch","2311");
		when(userService.authenticate("sch","2311")).thenReturn(true);
		when(userService.authenticate("sch","1234")).thenReturn(false);
		when(userService.authenticate(null,null)).thenThrow(LoginException.class);
		assertEquals(200,loginController.createAuthenticationToken(authenticationRequest).getBody().getStatus());
		authenticationRequest=new AuthenticationRequest("sch","1234");
		assertEquals(401,loginController.createAuthenticationToken(authenticationRequest).getBody().getStatus());
		authenticationRequest=new AuthenticationRequest(null,null);
		assertEquals(401,loginController.createAuthenticationToken(authenticationRequest).getBody().getStatus());
	}
	
	@Test
	public void validateValidTokenTest() throws LoginException {
		when(jwtUtil.validateToken(CommonTestConstants.VALID_JWT_TOKEN)).thenReturn(true);
		assertEquals(200, loginController.validateToken(CommonTestConstants.VALID_JWT_TOKEN).getBody().getStatus());
	}
	
	@Test
	public void validateInvalidTokenTest() throws LoginException {
		when(jwtUtil.validateToken(CommonTestConstants.INVALID_JWT_TOKEN)).thenThrow(LoginException.class);
		assertEquals(401, loginController.validateToken(CommonTestConstants.INVALID_JWT_TOKEN).getBody().getStatus());
	}
	
}
