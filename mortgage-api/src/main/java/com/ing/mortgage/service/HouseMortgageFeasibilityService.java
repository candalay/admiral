package com.ing.mortgage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ing.mortgage.dto.MortgageFeasibilityRequest;
import com.ing.mortgage.dto.MortgageFeasibilityResponse;

@Service("HouseMortgageFeasibilityService")
public class HouseMortgageFeasibilityService implements MortgageFeasibilityService {
	
	private static final Logger logger = LoggerFactory.getLogger(HouseMortgageFeasibilityService.class);
	
	@Autowired
	@Qualifier("HouseMortgageFeasibilityRequestValidator")
	private MortgageFeasibilityRequestValidator houseMortgageFeasibilityRequestValidator;
	
	@Autowired
	@Qualifier("HouseMortgageFeasibilityHandler")
	private MortgageFeasibilityHandler houseMortgageFeasibilityHandler;

	@Override
	public MortgageFeasibilityResponse checkMortgageFeasibility(MortgageFeasibilityRequest request) {
		
		logger.debug("Checking mortgage feasibility");
		
		houseMortgageFeasibilityRequestValidator.validate(request);
		
		return houseMortgageFeasibilityHandler.calculateFeasibility(request);
	}

}
