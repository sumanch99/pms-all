/**
 * 
 */
package com.cognizant.pms.gpd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author USER
 *
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
	
	private String bankName;
    private String accountNo;
    private String bankType;
}
