/**
 * August 2022
 */
package com.cognizant.pms.processpension.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.pms.processpension.model.Bank;

/**
 * @author Suman Chakraborty
 *
 */
@Repository
public interface PensionerBankRepository extends JpaRepository<Bank, String> {

}
