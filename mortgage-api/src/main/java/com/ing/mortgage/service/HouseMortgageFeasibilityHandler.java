package com.ing.mortgage.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.mortgage.dto.MortgageFeasibilityRequest;
import com.ing.mortgage.dto.MortgageFeasibilityResponse;
import com.ing.mortgage.repository.InterestRatesRepository;

@Service("HouseMortgageFeasibilityHandler")
public class HouseMortgageFeasibilityHandler implements MortgageFeasibilityHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(HouseMortgageFeasibilityHandler.class);
	
	@Autowired
	InterestRatesRepository interestRatesRepository;

	@Override
	public MortgageFeasibilityResponse calculateFeasibility(MortgageFeasibilityRequest request) {
		
		logger.debug("Calculating feasibility");
		
		Double interestRate = interestRatesRepository.findInterestRateByMaturityPeriod(request.getMaturityPeriod()).get(0).getRate();
		BigDecimal monthlyCost = calculateMonthlyCost(interestRate, request.getMaturityPeriod(), request.getLoanValue());
		
		MortgageFeasibilityResponse response = new MortgageFeasibilityResponse();
		response.setFeasibleMortgage(true);
		response.setMonthlyCost(monthlyCost);
		
		return response;
	}

	private BigDecimal calculateMonthlyCost(Double interestRate, Integer maturityPeriod, BigDecimal loanValue) {
		
		double a = (1 + (interestRate / 100 / 12));
		double result = ((interestRate / 100 / 12) * loanValue.doubleValue()) / (1 - Math.pow(a,(-maturityPeriod * 12)));
		
		return BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_DOWN);
	}

}
