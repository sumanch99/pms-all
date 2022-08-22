package com.cognizant.pms.gpd.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.pms.gpd.constant.CommonTestConstants;
import com.cognizant.pms.gpd.model.PensionerDetails;
import com.cognizant.pms.gpd.service.GetPensionerDetailsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetPensionerDetailsControllerTest {
	
	@MockBean
    private GetPensionerDetailsService service;
    
    @Autowired
    private GetPensionerDetailsController getPensionerDetailsController;
    
    @Before
    public void initialize() {
    	when(service.validateAuth(CommonTestConstants.VALID_JWT_TOKEN)).thenReturn(true);
    	when(service.validateAuth(CommonTestConstants.INVALID_JWT_TOKEN)).thenReturn(false);
    	when(service.findPensionDetailsFromCSV("123")).thenReturn(new PensionerDetails());
    }
	
	@Test
	public void getPensionerDetailsTestWithValidAuth() {
		assertEquals(200, getPensionerDetailsController.getPensionerDetails(CommonTestConstants.VALID_JWT_TOKEN, "123").getBody().getStatus());
	}
	
	@Test
	public void getPensionerDetailsTestWithInvalidAuth() {
		assertEquals(401, getPensionerDetailsController.getPensionerDetails(CommonTestConstants.INVALID_JWT_TOKEN, "123").getBody().getStatus());
	}

}
