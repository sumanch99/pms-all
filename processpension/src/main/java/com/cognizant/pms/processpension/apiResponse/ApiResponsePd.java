package com.cognizant.pms.processpension.apiResponse;

import com.cognizant.pms.processpension.model.PensionerDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponsePd {
    private int status;
    private String message;
    private PensionerDetails data;
}
