/**
 * 
 */
package com.cognizant.pms.processpension.bo;

import com.cognizant.pms.processpension.model.Bank;
import com.cognizant.pms.processpension.model.Pensioner;

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
@AllArgsConstructor
@NoArgsConstructor
public class PensionerDetails {
	Pensioner pensioner;
	Bank bank;
}
