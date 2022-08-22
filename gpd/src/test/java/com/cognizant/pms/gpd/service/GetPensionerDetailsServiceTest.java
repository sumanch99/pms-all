package com.cognizant.pms.gpd.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.pms.gpd.apiResponse.ApiResponse;
import com.cognizant.pms.gpd.constant.CommonTestConstants;
import com.cognizant.pms.gpd.feignclient.AuthInterface;
import com.cognizant.pms.gpd.model.Bank;
import com.cognizant.pms.gpd.model.Pensioner;
import com.cognizant.pms.gpd.model.PensionerDetails;
import com.cognizant.pms.gpd.util.GetPensionDetailsFromCsvUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetPensionerDetailsServiceTest {

	@Autowired
	GetPensionerDetailsService getPensionerDetailsService;

	@MockBean
	private GetPensionDetailsFromCsvUtil util;

	@MockBean
	private AuthInterface authClient; 

	@Before
	public void initialize() throws IOException, ParseException {
		when(util.getDetailsFromCSV()).thenReturn(Stream
				.of(new PensionerDetails(new Pensioner("123456789012", "Suman", new Date(), "BZRPC53547", 20000.0D,
						1200D, "SELF", "324233454"), new Bank("SBI", "324233454", "PUBLIC")))
				.collect(Collectors.toList()));
		when(authClient.validateAuthToken(CommonTestConstants.INVALID_JWT_TOKEN)).thenReturn(new ApiResponse(0,null,false));
		when(authClient.validateAuthToken(CommonTestConstants.VALID_JWT_TOKEN)).thenReturn(new ApiResponse(0,null,true));
	}
	
	@Test(expected = RuntimeException.class)
	public void findPensionDetailsFromCSVWithExceptionTest() {
		getPensionerDetailsService.findPensionDetailsFromCSV(null);
	}

	@Test
	public void findPensionDetailsFromCSVTest() {
		assertEquals("Suman",
				getPensionerDetailsService.findPensionDetailsFromCSV("123456789012").getPensioner().getName());
	}

	@Test
	public void validateAuthTest() {
		assertTrue(getPensionerDetailsService.validateAuth(CommonTestConstants.VALID_JWT_TOKEN));
	}

	@Test
	public void validateInvalidAuthTest() {
		assertFalse(getPensionerDetailsService.validateAuth(CommonTestConstants.INVALID_JWT_TOKEN));
	}

}
