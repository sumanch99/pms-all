/**
 * August 2022
 */
package com.cognizant.pms.processpension.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Suman Chakraborty
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PensionDetail {
	@Id
	private String aadharNo;
	private Double pensionAmount;
	private Double bankServiceCharge;
	
}
