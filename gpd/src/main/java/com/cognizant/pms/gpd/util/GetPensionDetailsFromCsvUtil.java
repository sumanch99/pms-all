package com.cognizant.pms.gpd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cognizant.pms.gpd.model.Bank;
import com.cognizant.pms.gpd.model.Pensioner;
import com.cognizant.pms.gpd.model.PensionerDetails;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GetPensionDetailsFromCsvUtil {

	public List<PensionerDetails> getDetailsFromCSV() throws IOException, ParseException {
		log.info("Entered getDetailsFromCSV()");
		BufferedReader br = new BufferedReader(
				new InputStreamReader(this.getClass().getResourceAsStream("/SAMPLEDATAEXCEL.csv")));
		log.info("Loaded SAMPLEDATAEXCEL.csv");
		String line = null;
		List<PensionerDetails> pensionerDetailsList = new ArrayList<>();

		while ((line = br.readLine()) != null) {
			log.info("line:" + line);
			String[] s = line.split(",");
			Pensioner pensioner = new Pensioner();
			pensioner.setAadharNo(s[0].trim());
			pensioner.setName(s[1].trim());
			pensioner.setDob(new SimpleDateFormat("dd-MM-yyyy").parse(s[2].trim()));

			pensioner.setPanNo(s[3].trim());
			pensioner.setSalary(Double.parseDouble(s[4].trim()));
			pensioner.setAllowance(Double.parseDouble(s[5].trim()));
			pensioner.setPensionType(s[6].trim());
			pensioner.setAccountNo(s[8].trim());
			Bank bank = new Bank();
			bank.setBankName(s[7].trim());
			bank.setAccountNo(s[8].trim());
			bank.setBankType(s[9].trim());

			PensionerDetails pensionerDetails = new PensionerDetails(pensioner, bank);
			pensionerDetailsList.add(pensionerDetails);
		}
		log.info("SAMPLEDATAEXCEL loaded into pensionerDetailsList, size:" + pensionerDetailsList.size());
		log.info("Exiting getDetailsFromCSV()");
		return pensionerDetailsList;
	}

}
