/**
 * August 2022
 */
package com.cognizant.pms.processpension.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.cognizant.pms.processpension.exception.ProcessPensionException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Suman Chakraborty
 *
 */
@Slf4j
public class CalculatePensionUtil {
	
	private CalculatePensionUtil() {}

	public static Double calculatePension(String pensionType, String bankName, String bankType, Double lastSalaryEarned,
			Double allowances) {
		log.info("Called calculatePension()");
		Double pensionAmount = 0D;
		if (CommonConstants.SELF_PENSION.equals(pensionType)) {
			pensionAmount = 0.8 * lastSalaryEarned + allowances;
			log.info("pensionAmount for SELF_PENSION:" + pensionAmount);
		} else if (CommonConstants.FAMILY_PENSION.equals(pensionType)) {
			pensionAmount = 0.5 * lastSalaryEarned + allowances;
			log.info("pensionAmount for FAMILY_PENSION:" + pensionAmount);
		}
		try {
			pensionAmount -= getBankServiceChargeFromCSV(bankName);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ProcessPensionException(e.getMessage());
		}
		log.info("Exiting calculatePension()");
		return pensionAmount;
	}

	public static Double getBankServiceChargeFromCSV(String bankName) throws NumberFormatException, IOException {
		log.info("Called getBankServiceChargeFromCSV() for bank:" + bankName);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				CalculatePensionUtil.class.getResourceAsStream(CommonConstants.FILE_BANKSERVICECHARGES)));
		log.info("BANKSERVICECHARGES.csv loaded");
		String line = null;
		Double serviceCharge = 0D;
		while ((line = br.readLine()) != null) {
			log.info("line:" + line);
			String[] s = line.split(",");
			if (s[1].trim().equals(bankName)) {
				serviceCharge = Double.parseDouble(s[3].trim());
			}
		}
		log.info("serviceCharge:" + serviceCharge);
		return serviceCharge;
	}

}
