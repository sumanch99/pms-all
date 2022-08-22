package com.cognizant.pms.processpension.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cognizant.pms.processpension.apiResponse.ApiResponsePd;

@FeignClient(name="getPensionerDetails",url="${GET_PENSIONER_DETAILS_URI:http://localhost:9001}")
public interface GpdInterface {
    @RequestMapping(value = "/PensionerDetailByAadhaar/{aadharNo}", method = RequestMethod.GET)
    public ApiResponsePd getPensionerDetails(@RequestHeader("Authorization") String authToken, @PathVariable("aadharNo") String aadharNo);
}
