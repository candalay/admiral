package com.ing.mortgage.test.service;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ing.mortgage.controller.MortgageCalculationController;

public abstract class AbstractMortgageApiServiceTest {

	@Autowired
	protected WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;
	
	protected String expectedInterestRateResult;
	
	protected String expectedMortgageFeasibilityResult;

	@InjectMocks
	protected MortgageCalculationController mortgageCalculationController;

	Logger logger = LoggerFactory.getLogger(AbstractMortgageApiServiceTest.class);

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		expectedInterestRateResult = getInterestRateExpectedResult();
		expectedMortgageFeasibilityResult = getMortgageFeasibilityResult();
	}

	private String getMortgageFeasibilityResult() {
		
		String result = "{\"mortgageFeasibility\":{\"feasibleMortgage\":true,\"monthlyCost\":1583.46}}";
		
		return result;
	}

	private String getInterestRateExpectedResult() {
		String result = "{\"interestRates\":[{\"maturityPeriod\":50,\"interestRate\":7.9,\"lastUpdatedDate\":\"2018-01-25 18:13:57.759\"},"
		                                   + "{\"maturityPeriod\":30,\"interestRate\":6.52,\"lastUpdatedDate\":\"2018-01-25 18:13:57.764\"},"
		                                   + "{\"maturityPeriod\":20,\"interestRate\":2.8,\"lastUpdatedDate\":\"2018-01-25 18:13:57.764\"},"
		                                   + "{\"maturityPeriod\":15,\"interestRate\":2.6,\"lastUpdatedDate\":\"2018-01-25 18:13:57.764\"},"
		                                   + "{\"maturityPeriod\":10,\"interestRate\":2.2,\"lastUpdatedDate\":\"2018-01-25 18:13:57.765\"},"
		                                   + "{\"maturityPeriod\":5,\"interestRate\":1.75,\"lastUpdatedDate\":\"2018-01-25 18:13:57.765\"}]}";
		return result;
	}

}
