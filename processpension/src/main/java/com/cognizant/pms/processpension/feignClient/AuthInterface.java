package com.cognizant.pms.processpension.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cognizant.pms.processpension.apiResponse.ApiResponse;

@FeignClient(name="login",url="${LOGIN_URI:http://localhost:9003}")
public interface AuthInterface {
	@RequestMapping(value = "/login/validate-token", method = RequestMethod.GET)
    public ApiResponse validateAuthToken(@RequestHeader("Authorization") String authToken);
}
