/**
 * 
 */
package com.cognizant.pms.processpension.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pensioner {
	@Id
	private String aadharNo;
    private String name;
    private Date dob;
    private String panNo;
    private Double salary;
    private Double allowance;
    private String pensionType;
    private String accountNo;
	
}
