package com.cognizant.pms.processpension.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PensionerTest {
	
	@Test
	public void testBankModel() {
		Pensioner pensioner=new Pensioner();
		pensioner.setAadharNo("123");
		pensioner.setAccountNo("123");
		pensioner.setAllowance(200.0D);
		pensioner.setDob(new Date());
		pensioner.setName("abc");
		pensioner.setPanNo("abcd");
		pensioner.setPensionType("self");
		pensioner.setSalary(100000.0D);
		assertEquals("123", pensioner.getAadharNo());
		assertEquals("123", pensioner.getAccountNo());
		assertEquals(200.0D, pensioner.getAllowance());
		assertNotNull(pensioner.getDob());
		assertEquals("abc", pensioner.getName());
		assertEquals("abcd", pensioner.getPanNo());
		assertEquals("self", pensioner.getPensionType());	
		assertEquals(100000.0D, pensioner.getSalary());	
		
		Pensioner p = Pensioner.builder().build();
		assertNotNull(p);
		p.toString();
		Pensioner pensionerBuilder = Pensioner.builder().aadharNo("12").accountNo("12").allowance(100.0D).dob(new Date()).name("ab").panNo("abc").pensionType("self").salary(100000.0D).build();
		Pensioner.builder().toString();
		assertNotNull(pensionerBuilder);
		
	}
	
}
