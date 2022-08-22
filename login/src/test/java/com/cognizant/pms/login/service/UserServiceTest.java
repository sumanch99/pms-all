package com.cognizant.pms.login.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.pms.login.apiRequest.AuthenticationRequest;
import com.cognizant.pms.login.exception.LoginException;
import com.cognizant.pms.login.model.User;
import com.cognizant.pms.login.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test(expected = LoginException.class)
	public void createInvalidNewAccountTest() throws LoginException {
		AuthenticationRequest request = new AuthenticationRequest("sch","2311");
		when(userRepository.findByUserName("sch")).thenReturn(Optional.of(new User()));
		userService.createNewAccount(request);
	}
	
	@Test
	public void createNewAccountTest() throws LoginException {
		AuthenticationRequest request = new AuthenticationRequest("suman","1234");
		when(userRepository.findByUserName("suman")).thenReturn(Optional.empty());
		when(userRepository.saveAndFlush(ArgumentMatchers.any(User.class))).thenReturn(new User());
		assertNotNull(userService.createNewAccount(request));
	}
	
	@Test
	public void authenticateValidTest() throws LoginException {
		when(userRepository.findByUserName("sch")).thenReturn(Optional.of(new User(12345,"sch",passwordEncoder.encode("2311"))));
		assertTrue(userService.authenticate("sch","2311"));
	}
	
	@Test
	public void authenticateInvalidPasswordTest() throws LoginException {
		when(userRepository.findByUserName("sch")).thenReturn(Optional.of(new User(12345,"sch",passwordEncoder.encode("2311"))));
		assertFalse(userService.authenticate("sch","1234"));
	}
	
	@Test(expected = LoginException.class)
	public void authenticateInvalidUsernameTest() throws LoginException {
		when(userRepository.findByUserName("suman")).thenReturn(Optional.empty());
		userService.authenticate("suman","1234");
	}
	
}
