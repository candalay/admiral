package com.ing.mortgage.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ing.mortgage.MortgageConstants;
import com.ing.mortgage.dto.MortgageFeasibilityRequest;
import com.ing.mortgage.exception.MortgageBusinessException;
import com.ing.mortgage.exception.MortgageMissingParameterException;

@Service("HouseMortgageFeasibilityRequestValidator")
public class HouseMortgageFeasibilityRequestValidator implements MortgageFeasibilityRequestValidator {
	
	private static final Logger logger = LoggerFactory.getLogger(HouseMortgageFeasibilityRequestValidator.class);

	@Override
	public void validate(MortgageFeasibilityRequest request) {
		
		logger.debug("Validating the feasibility request");

		if (request.getIncome() == null) {
			throw new MortgageMissingParameterException("ValidationError! Income is missing");
		}

		if (request.getLoanValue() == null) {
			throw new MortgageMissingParameterException("ValidationError! Loan value is missing");
		}

		if (request.getHomeValue() == null) {
			throw new MortgageMissingParameterException("ValidationError! Home value is missing");
		}

		if (request.getMaturityPeriod() == null) {
			throw new MortgageMissingParameterException("ValidationError! Maturity period is missing");
		}

		BigDecimal times = new BigDecimal(MortgageConstants.MORTGAGE_MULTIPLY_VALUE);

		if (request.getLoanValue().compareTo(request.getIncome().multiply(times)) > 0) {
			throw new MortgageBusinessException("A mortgage should not exceed 4 times the income");
		}

		if (request.getLoanValue().compareTo(request.getHomeValue()) > 0) {
			throw new MortgageBusinessException("A mortgage should not exceed the home value");
		}

	}

}
