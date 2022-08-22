package com.cognizant.pms.processpension.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PensionDetailTest {
	
	@Test
	public void testPensionDetailModel() {
		PensionDetail pensionDetail = new PensionDetail();
		pensionDetail.setAadharNo("123456789101");
		pensionDetail.setBankServiceCharge(500.0D);
		pensionDetail.setPensionAmount(25000.0D);
		
		assertEquals("123456789101", pensionDetail.getAadharNo());
		assertEquals(500.0D, pensionDetail.getBankServiceCharge());
		assertEquals(25000.0D, pensionDetail.getPensionAmount());
		
		PensionDetail pensionDetail1 = new PensionDetail("12345678909",550.0D,20000.0D);
		assertNotNull(pensionDetail1);
	}
	
}
