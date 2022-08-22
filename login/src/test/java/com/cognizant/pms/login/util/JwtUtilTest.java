package com.cognizant.pms.login.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.pms.login.exception.LoginException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtUtilTest {
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Test
	public void testGenerateToken() {
		String token = jwtUtil.generateToken("sch");
		assertEquals("sch", jwtUtil.extractUsername(token));
		assertNotNull(jwtUtil.extractExpiration(token));
	}
	
	@Test
	public void testValidateToken() throws LoginException {
		String token = "Bearer "+jwtUtil.generateToken("sch");
		assertNotNull(jwtUtil.validateToken(token));
	}
	
	@Test(expected = LoginException.class)
	public void testInvalidToken() throws LoginException {
		String token = jwtUtil.generateToken("sch");
		jwtUtil.validateToken(token);
	}
	
	@Test(expected = LoginException.class)
	public void testInvalidToken2() throws LoginException {
		String token = "Be "+jwtUtil.generateToken("sch");
		jwtUtil.validateToken(token);
	}
	
}
