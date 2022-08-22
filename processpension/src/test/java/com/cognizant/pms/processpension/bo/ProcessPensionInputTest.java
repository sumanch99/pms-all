package com.cognizant.pms.processpension.bo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessPensionInputTest {

	@Test
	public void testProcessPensionInputBo() {
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		processPensionInput.setAadharNo("123456789097");
		assertNotNull(processPensionInput.getAadharNo());
		ProcessPensionInput ppi = new ProcessPensionInput("567890567347");
		assertNotNull(ppi);
	}

}
