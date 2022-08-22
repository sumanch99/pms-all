package com.cognizant.pms.login.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ErrorResponseDtoTest {
	
	@Test
	public void testErrorResponseDto() {
	ErrorResponseDto errorResponseDto = new ErrorResponseDto();
	errorResponseDto.setDetails(null);
	errorResponseDto.setError("error");
	errorResponseDto.setPath("ab");
	errorResponseDto.setStatus(0);
	errorResponseDto.setTimestamp(null);
	assertEquals(null, errorResponseDto.getDetails());
	assertEquals("error", errorResponseDto.getError());
	assertEquals("ab", errorResponseDto.getPath());
	assertEquals(0, errorResponseDto.getStatus());
	assertEquals(null,errorResponseDto.getTimestamp());
	ErrorResponseDto e =new ErrorResponseDto(null,0,null,null,null);
	assertNotNull(e);

}
}