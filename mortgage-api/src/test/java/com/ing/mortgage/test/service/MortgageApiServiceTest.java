package com.ing.mortgage.test.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;


@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class MortgageApiServiceTest extends AbstractMortgageApiServiceTest {
	
	Logger logger = LoggerFactory.getLogger(MortgageApiServiceTest.class);
	
	@Test
	public void getInterestRatesTest() throws Exception {

		MvcResult result = mockMvc.perform(get("/mortgage/v1/api/interest-rates").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultInJson = result.getResponse().getContentAsString();
		
		Assert.assertTrue("Results did not match", expectedInterestRateResult.equals(resultInJson));
	}

	@Test
	public void checkMortgageFeasibilityTest() throws Exception {

		String mortgageFeasibilityRequest = "{ \"homeValue\": 350000, \"income\": 90000, \"loanValue\": 250000, \"maturityPeriod\": 30 }";
		MvcResult result = mockMvc.perform(post("/mortgage/v1/api/mortgage-check").contentType(MediaType.APPLICATION_JSON).content(mortgageFeasibilityRequest))
				.andExpect(status().isOk()).andReturn();
		String resultInJson = result.getResponse().getContentAsString();
		
		Assert.assertTrue("Results did not match", expectedMortgageFeasibilityResult.equals(resultInJson));
	}
}
