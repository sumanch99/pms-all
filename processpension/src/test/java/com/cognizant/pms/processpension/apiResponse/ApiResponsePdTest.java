package com.cognizant.pms.processpension.apiResponse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.pms.processpension.model.PensionerDetails;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiResponsePdTest {
	@Test
	public void testApiResponsePd() {
		ApiResponsePd ap=new ApiResponsePd();
		ap.setStatus(200);
		ap.setMessage("success");
		ap.setData(new PensionerDetails());
		assertNotNull(ap.getStatus());
		assertNotNull(ap.getMessage());
		assertNotNull(ap.getData());
		ApiResponsePd ap2 = ApiResponsePd.builder().build();
		assertNotNull(ap2);
		ap2.toString();
		ApiResponsePd apiResponsePdBuilder = ApiResponsePd.builder().status(200).message("Success").data(new PensionerDetails()).build();
		ApiResponsePd.builder().toString();
		assertNotNull(apiResponsePdBuilder);
	}

}