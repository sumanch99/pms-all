package com.cognizant.pms.gpd.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetPensionDetailsFromCsvUtilTest {
	
	@Autowired
	GetPensionDetailsFromCsvUtil getPensionDetailsFromCsvUtil;
	
	@Test
	public void testGetDetailsFromCSV() throws IOException, ParseException {
		assertEquals(18, getPensionDetailsFromCsvUtil.getDetailsFromCSV().size());
	}
	
}
