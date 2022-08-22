package com.cognizant.pms.processpension.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankTest {
	
	@Test
	public void testBankModel() {
		Bank bank = new Bank();
		bank.setAccountNo("123456789");
		bank.setBankName("SBI");
		bank.setBankType("PUBLIC");
		assertEquals("123456789", bank.getAccountNo());
		assertEquals("SBI", bank.getBankName());
		assertEquals("PUBLIC", bank.getBankType());
		Bank b = Bank.builder().build();
		assertNotNull(b);
		b.toString();
		Bank bankBuilder = Bank.builder().accountNo("123456789").bankName("SBI").bankType("PUBLIC").build();
		Bank.builder().toString();
		assertNotNull(bankBuilder);
	}
	
}
