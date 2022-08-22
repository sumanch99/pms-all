package com.cognizant.pms.gpd.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pms.gpd.apiResponse.ApiResponse;
import com.cognizant.pms.gpd.service.GetPensionerDetailsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/PensionerDetailByAadhaar")
@Slf4j
@CrossOrigin("*")
public class GetPensionerDetailsController {
    @Autowired
    private GetPensionerDetailsService service;

    @GetMapping(value = "/{aadharNo}")
    public ResponseEntity<ApiResponse> getPensionerDetails(@RequestHeader("Authorization") String authToken,@PathVariable String aadharNo){
    	log.info("Entered getPensionerDetails() for aadharNo:"+aadharNo);
    	log.info("authToken:"+authToken);
    	if(service.validateAuth(authToken)) {
    		log.info("Authentication validated");
    		ResponseEntity<ApiResponse> response = ResponseEntity.ok(ApiResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Details Fetched")
                    .data(service.findPensionDetailsFromCSV(aadharNo))
            .build());
    		log.info("response:"+response);
    		log.info("Exiting getPensionerDetails() for aadharNo:"+aadharNo);
    		return response;
        }
    	ResponseEntity<ApiResponse> response = ResponseEntity.ok(ApiResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Incorrect Credentials")
                .data(null)
                .build());
    	log.error("Authentication Failed");
    	log.info("response:"+response);
    	log.info("Exiting getPensionerDetails() for aadharNo:"+aadharNo);
    	return response;
    }
}
