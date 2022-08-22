package com.cognizant.pms.login.apiResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiResponseTest {
	@Test
	public void testApiResponse() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setData(apiResponse);
		apiResponse.setMessage("abc");
		apiResponse.setStatus(200);
		assertEquals(apiResponse, apiResponse.getData());
		assertEquals("abc", apiResponse.getMessage());
		assertEquals(200, apiResponse.getStatus());
		ApiResponse a = ApiResponse.builder().build();
		assertNotNull(a);
		a.toString();
		ApiResponse apiResponseBuilder = ApiResponse.builder().data(a).status(200).message("abc").build();
		ApiResponse.builder().toString();
		assertNotNull(apiResponseBuilder);
	}
}
