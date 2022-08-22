/**
 * 
 */
package com.cognizant.pms.processpension.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Suman Chakraborty
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PensionerDetails {
	Pensioner pensioner;
	Bank bank;
}
