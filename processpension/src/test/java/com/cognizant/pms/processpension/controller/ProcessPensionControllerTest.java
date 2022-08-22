package com.cognizant.pms.processpension.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.pms.processpension.bo.ProcessPensionInput;
import com.cognizant.pms.processpension.constant.CommonTestConstants;
import com.cognizant.pms.processpension.exception.ProcessPensionException;
import com.cognizant.pms.processpension.service.ProcessPensionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessPensionControllerTest {
	
	@Autowired
    private ProcessPensionController processPensionController;
	
	@MockBean
	private ProcessPensionService service;
	
	@Before
    public void initialize() {
    	when(service.validateAuth(CommonTestConstants.VALID_JWT_TOKEN)).thenReturn(true);
    	when(service.validateAuth(CommonTestConstants.INVALID_JWT_TOKEN)).thenReturn(false);
    	when(service.processPension(CommonTestConstants.VALID_JWT_TOKEN, "112233445566")).thenThrow(ProcessPensionException.class);
    	when(service.getPensionerDetailsByAadhar("112233445566")).thenThrow(ProcessPensionException.class);
	}
	
	@Test
	public void proessPensionWithValidAuth() {
		ProcessPensionInput input = new ProcessPensionInput("123456789012");
		assertEquals(200, processPensionController.proessPension(CommonTestConstants.VALID_JWT_TOKEN, input).getBody().getStatus());
	}
	
	@Test
	public void proessPensionWithInvalidAuth() {
		ProcessPensionInput input = new ProcessPensionInput("123456789012");
		assertEquals(401, processPensionController.proessPension(CommonTestConstants.INVALID_JWT_TOKEN, input).getBody().getStatus());
	}
	
	@Test
	public void getPensionDetailsOfPensionerWithAlreadyProceedAadhar() {
		ProcessPensionInput input = new ProcessPensionInput("112233445566");
		assertEquals(400, processPensionController.proessPension(CommonTestConstants.VALID_JWT_TOKEN, input).getBody().getStatus());
	}
	
	@Test
	public void getPensionDetailsOfPensionerWithValidAuth() {
		assertEquals(200, processPensionController.getPensionDetailsOfPensioner(CommonTestConstants.VALID_JWT_TOKEN, "123456789012").getBody().getStatus());
	}
	
	@Test
	public void getPensionDetailsOfPensionerWithInvalidAuth() {
		assertEquals(401, processPensionController.getPensionDetailsOfPensioner(CommonTestConstants.INVALID_JWT_TOKEN, "123456789012").getBody().getStatus());
	}
	
	@Test
	public void proessPensionWithAlreadyProceedAadhar() {
		assertEquals(400, processPensionController.getPensionDetailsOfPensioner(CommonTestConstants.VALID_JWT_TOKEN, "112233445566").getBody().getStatus());
	}
	
}
