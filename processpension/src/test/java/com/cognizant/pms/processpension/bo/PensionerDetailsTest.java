package com.cognizant.pms.processpension.bo;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.pms.processpension.model.Bank;
import com.cognizant.pms.processpension.model.Pensioner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PensionerDetailsTest {
	
	@Test
	public void testPensionerDetailsBo() {
		PensionerDetails pd = new PensionerDetails();
		pd.setBank(new Bank());
		pd.setPensioner(new Pensioner());
		assertNotNull(pd.getBank());
		assertNotNull(pd.getPensioner());
		
		PensionerDetails pd1 = new PensionerDetails(new Pensioner(),new Bank());
		assertNotNull(pd1);
	}
	
}
