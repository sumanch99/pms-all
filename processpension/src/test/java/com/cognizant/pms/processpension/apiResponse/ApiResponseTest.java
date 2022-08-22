package com.cognizant.pms.processpension.apiResponse;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiResponseTest {
	@Test
	public void testGettersSettersConstructors() {
		ApiResponse res = ApiResponse.builder()
		        .status(0)
		        .message(null)
		        .data(null).build();
		res.setData("Suman");
		res.setMessage("Msg");
		res.setStatus(200);
		assertNotNull(res);
		assertEquals("Suman", res.getData());
		assertEquals("Msg", res.getMessage());
		assertEquals(200, res.getStatus());
		ApiResponse res2 = new ApiResponse();
		assertNotNull(res);
		res.equals(res2);
		res.toString();
		res.hashCode();
		res.equals(new Object());
		res.equals(null);
		res2.hashCode();
		ApiResponse.builder().toString();
	}
}
