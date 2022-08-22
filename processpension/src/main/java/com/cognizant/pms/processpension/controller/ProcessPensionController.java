package com.cognizant.pms.processpension.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pms.processpension.apiResponse.ApiResponse;
import com.cognizant.pms.processpension.bo.ProcessPensionInput;
import com.cognizant.pms.processpension.model.PensionDetail;
import com.cognizant.pms.processpension.service.ProcessPensionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/ProcessPension")
@CrossOrigin("*")
public class ProcessPensionController {

    @Autowired
    private ProcessPensionService service;

    @PostMapping("/") 
    public ResponseEntity<ApiResponse> proessPension(@RequestHeader("Authorization") String authToken,@RequestBody ProcessPensionInput input){
    	log.info("Entered proessPension() for input:"+input);
    	log.info("authToken:"+authToken);
    	if(service.validateAuth(authToken)) {
    		log.info("authToken validated successfully");
    		try {
    			PensionDetail pd = service.processPension(authToken, input.getAadharNo());
        		ResponseEntity<ApiResponse> response = ResponseEntity.ok(ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Pension Processed successfully")
                        .data(pd)
                        .build());
        		log.info("response:"+response);
        		log.info("Exiting proessPension() for input:"+input);
        		return response;
    		}catch(Exception e) {
    			log.error(e.getMessage());
    			ResponseEntity<ApiResponse> response = ResponseEntity.ok(ApiResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .data(null)
                        .build());
    			log.info("response:"+response);
    			return response;
    		}
        }
    	log.info("authToken validation failed");
    	ResponseEntity<ApiResponse> response =  ResponseEntity.ok(ApiResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Incorrect Credentials")
                .data(false)
                .build());
    	log.error("response:"+response);
    	log.error("Exiting proessPension() for input:"+input);
    	return response;
    }

    @GetMapping("/{aadharNo}")
    public ResponseEntity<ApiResponse> getPensionDetailsOfPensioner(@RequestHeader("Authorization") String authToken, @PathVariable("aadharNo") String aadharNo){
    	log.info("Entered getPensionDetailsOfPensioner() for aadharNo:"+aadharNo);
    	log.info("authToken:"+authToken);
        if(service.validateAuth(authToken)) {
        	try {
        		ResponseEntity<ApiResponse> response =   ResponseEntity.ok(ApiResponse.builder()
                        .status(HttpStatus.OK.value())
                        .message("Fetched from Database")
                        .data(service.getPensionerDetailsByAadhar(aadharNo))
                        .build());
            	log.info("response:"+response);
            	log.info("Exiting getPensionDetailsOfPensioner() for aadharNo:"+aadharNo);
            	return response;
        	}catch(Exception e) {
        		log.error(e.getMessage());
    			ResponseEntity<ApiResponse> response = ResponseEntity.ok(ApiResponse.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage())
                        .data(null) 
                        .build());
    			log.info("response:"+response);
    			return response;
        	}
        	
        }
        ResponseEntity<ApiResponse> response =   ResponseEntity.ok(ApiResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Incorrect Credentials")
                .data(false)
                .build());
        log.error("response:"+response);
        log.error("Exiting getPensionDetailsOfPensioner() for aadharNo:"+aadharNo);
        return response;
    }
}
