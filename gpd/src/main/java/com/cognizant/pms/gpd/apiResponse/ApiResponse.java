package com.cognizant.pms.gpd.apiResponse;

import java.io.Serializable;

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
public class ApiResponse implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 387128873189408487L;
	private int status;
    private String message;
    private Object data;
}
