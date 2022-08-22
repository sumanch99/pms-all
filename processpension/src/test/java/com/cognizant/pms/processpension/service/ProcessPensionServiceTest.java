package com.cognizant.pms.processpension.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Matchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.pms.processpension.apiResponse.ApiResponse;
import com.cognizant.pms.processpension.apiResponse.ApiResponsePd;
import com.cognizant.pms.processpension.constant.CommonTestConstants;
import com.cognizant.pms.processpension.exception.ProcessPensionException;
import com.cognizant.pms.processpension.feignClient.AuthInterface;
import com.cognizant.pms.processpension.feignClient.GpdInterface;
import com.cognizant.pms.processpension.model.Bank;
import com.cognizant.pms.processpension.model.PensionDetail;
import com.cognizant.pms.processpension.model.Pensioner;
import com.cognizant.pms.processpension.model.PensionerDetails;
import com.cognizant.pms.processpension.repository.PensionDetailRepository;
import com.cognizant.pms.processpension.repository.PensionerBankRepository;
import com.cognizant.pms.processpension.repository.PensionerRepository;
import com.cognizant.pms.processpension.util.CalculatePensionUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessPensionServiceTest {

	@Autowired
	private ProcessPensionService processPensionService;

	@MockBean
	private PensionDetailRepository pensionDetailRepository;

	@MockBean
	private PensionerRepository pensionerRepository;

	@MockBean
	private PensionerBankRepository bankRepository;

	@MockBean
	private AuthInterface authClient;

	@MockBean
	private GpdInterface gpdClient;

	@Before
	public void initialize() throws IOException, ParseException {
		when(authClient.validateAuthToken(CommonTestConstants.INVALID_JWT_TOKEN)).thenReturn(new ApiResponse(0,null,false));
		when(authClient.validateAuthToken(CommonTestConstants.VALID_JWT_TOKEN)).thenReturn(new ApiResponse(0,null,true));
		when(gpdClient.getPensionerDetails(CommonTestConstants.VALID_JWT_TOKEN, "112233445566"))
				.thenReturn(new ApiResponsePd());
		when(pensionDetailRepository.findByAadharNo("123456789012")).thenReturn(Optional.of(new PensionDetail()));
		when(gpdClient.getPensionerDetails(CommonTestConstants.VALID_JWT_TOKEN, "123456789012"))
				.thenReturn(new ApiResponsePd(200, "Data fetched", new PensionerDetails()));
		when(gpdClient.getPensionerDetails(CommonTestConstants.VALID_JWT_TOKEN, "520552812599"))
				.thenReturn(new ApiResponsePd(200, "Data fetched",
						new PensionerDetails(
								new Pensioner("520552812599", "Suman", new Date(), null, 30000D, 5000D, "SELF", null),
								new Bank(null, "SBI", "PUBLIC"))));
		when(gpdClient.getPensionerDetails(CommonTestConstants.VALID_JWT_TOKEN, "555557777756"))
		.thenReturn(new ApiResponsePd(200, "Data fetched",
				new PensionerDetails(
						new Pensioner("520552812599", "Suman", new Date(), null, 30000D, 5000D, "SELF", null),
						new Bank(null, "INVALID", "PUBLIC"))));
	}

	@Test
	public void validateAuthTest() {
		assertTrue(processPensionService.validateAuth(CommonTestConstants.VALID_JWT_TOKEN));
	}

	@Test
	public void validateInvalidAuthTest() {
		assertFalse(processPensionService.validateAuth(CommonTestConstants.INVALID_JWT_TOKEN));
	}

	@Test(expected = ProcessPensionException.class)
	public void testProcessPensionWithInvalidAadhar() {
		processPensionService.processPension(CommonTestConstants.VALID_JWT_TOKEN, "112233445566");
	}

	@Test(expected = ProcessPensionException.class)
	public void testProcessPensionWithAlreadyProcessedAadhar() {
		processPensionService.processPension(CommonTestConstants.VALID_JWT_TOKEN, "123456789012");
	}

	@Test
	public void testProcessPension() {
		try (MockedStatic<CalculatePensionUtil> mockStatic = Mockito.mockStatic(CalculatePensionUtil.class)) {
			mockStatic.when(() -> CalculatePensionUtil.calculatePension("SELF", "SBI", "PUBLIC", 30000D, 5000D))
					.thenReturn(100D);
			mockStatic.when(() -> CalculatePensionUtil.getBankServiceChargeFromCSV("SBI"))
			.thenReturn(500D);
			when(pensionerRepository.saveAndFlush(ArgumentMatchers.any(Pensioner.class))).thenReturn(new Pensioner());
			when(pensionDetailRepository.saveAndFlush(ArgumentMatchers.any(PensionDetail.class))).thenReturn(new PensionDetail());
			when(bankRepository.saveAndFlush(ArgumentMatchers.any(Bank.class))).thenReturn(new Bank());
			processPensionService.processPension(CommonTestConstants.VALID_JWT_TOKEN, "520552812599");
		}

	}
	
	@Test(expected = ProcessPensionException.class)
	public void testProcessPensionWithInvalidBank() {
		try (MockedStatic<CalculatePensionUtil> mockStatic = Mockito.mockStatic(CalculatePensionUtil.class)) {
			mockStatic.when(() -> CalculatePensionUtil.calculatePension("SELF", "INVALID", "PUBLIC", 30000D, 5000D))
					.thenReturn(0D);
			mockStatic.when(() -> CalculatePensionUtil.getBankServiceChargeFromCSV("INVALID"))
			.thenThrow(RuntimeException.class);
			processPensionService.processPension(CommonTestConstants.VALID_JWT_TOKEN, "555557777756");
		}

	}
	
	@Test
	public void testGetPensionerDetailsByAadharWithValidAadhar() {
		processPensionService.getPensionerDetailsByAadhar("123456789012");
	}
	
}
