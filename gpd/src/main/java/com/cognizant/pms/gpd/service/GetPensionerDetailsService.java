package com.cognizant.pms.gpd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.pms.gpd.exception.GetPensionDetailsException;
import com.cognizant.pms.gpd.feignclient.AuthInterface;
import com.cognizant.pms.gpd.model.PensionerDetails;
import com.cognizant.pms.gpd.util.GetPensionDetailsFromCsvUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GetPensionerDetailsService {
	
    @Autowired
    private GetPensionDetailsFromCsvUtil util;
    
    @Autowired
    AuthInterface authClient;
    
    public boolean validateAuth(String authToken) {
    	log.info("Entered validateAuth()");
		try {
			log.info("Token Validated");
			log.info("Exiting validateAuth()");
			return (Boolean)authClient.validateAuthToken(authToken).getData();
		}catch(Exception e) {
			log.error(e.getMessage());
			return false;
		}
		
		
	}
    
    public PensionerDetails findPensionDetailsFromCSV(String aadharNo){
    	log.info("Entered findPensionDetailsFromCSV() for aadharNo:"+aadharNo);
        try {
        	if(aadharNo==null) {
        		throw new GetPensionDetailsException("Adhaar No is null");
        	}
            return util.getDetailsFromCSV().stream().filter(e->e.getPensioner().getAadharNo().equals(aadharNo)).findAny()
                    .orElse(null);
        } catch (Exception e) {
        	log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
