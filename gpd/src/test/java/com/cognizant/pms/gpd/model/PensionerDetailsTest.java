package com.cognizant.pms.gpd.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PensionerDetailsTest {
	
	@Test
	public void testPensionerDetailsModel() {
		PensionerDetails pd = new PensionerDetails();
		pd.setBank(new Bank());
		pd.setPensioner(new Pensioner());
		assertNotNull(pd.getBank());
		assertNotNull(pd.getPensioner());
		PensionerDetails pd2 = PensionerDetails.builder().build();
		assertNotNull(pd2);
		pd2.toString();
		PensionerDetails pensionerDetailsBuilder = PensionerDetails.builder().bank(new Bank()).pensioner(new Pensioner()).build();
		PensionerDetails.builder().toString();
		assertNotNull(pensionerDetailsBuilder);
	}
	
}
