package com.cognizant.pms.processpension.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatePensinUtil {
	
	@Test
	public void testSelfPension() {
		assertEquals(28500.0, CalculatePensionUtil.calculatePension("SELF","SBI", "PUBLIC", 30000D, 5000D));
	}
	
	@Test
	public void testFamilyPension() {
		assertEquals(19500.0, CalculatePensionUtil.calculatePension("FAMILY","SBI", "PUBLIC", 30000D, 5000D));
	}
	
	/*@Test(expected = ProcessPensionException.class)
	public void testIncorrectBankName() throws NumberFormatException, IOException {
		try (MockedStatic<CalculatePensionUtil> utilities = Mockito.mockStatic(CalculatePensionUtil.class)) {
	        utilities.when(CalculatePensionUtil::getBankServiceChargeFromCSV("INVALID")).thenThrow(RuntimeException.class);
	        //assertThat(CalculatePensionUtil.name()).isEqualTo("Eugen");
	    }
		when(CalculatePensionUtil.getBankServiceChargeFromCSV("INVALID")).thenThrow(RuntimeException.class);
		CalculatePensionUtil.calculatePension("FAMILY","INVALID", "PUBLIC", 30000D, 5000D);
	}*/
}
