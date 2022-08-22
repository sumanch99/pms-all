/**
 * 
 */
package com.cognizant.pms.gpd.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
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
@AllArgsConstructor
@NoArgsConstructor
public class PensionerDetails {
	Pensioner pensioner;
	Bank bank;
}
