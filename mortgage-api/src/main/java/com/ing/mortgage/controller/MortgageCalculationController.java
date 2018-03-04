package com.ing.mortgage.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ing.mortgage.MortgageConstants;
import com.ing.mortgage.dto.MortgageFeasibilityRequest;
import com.ing.mortgage.service.InterestRateService;
import com.ing.mortgage.service.MortgageFeasibilityService;

@RestController
@RequestMapping("/mortgage/v1/api")
public class MortgageCalculationController {

	private static final Logger logger = LoggerFactory.getLogger(MortgageCalculationController.class);

	@Autowired
	@Qualifier("MortgageInterestRateService")
	InterestRateService mortgageInterestRateService;

	@Autowired
	@Qualifier("HouseMortgageFeasibilityService")
	MortgageFeasibilityService houseMortgageFeasibilityService;
	
	@RequestMapping(value = "/interest-rates", method = RequestMethod.GET)
	public Map<String, Object> listInterestRates() {

		logger.debug("listing interest rates for mortage");
		
		Map<String, Object> response = new HashMap<>();
		response.put(MortgageConstants.INTEREST_RATES, mortgageInterestRateService.getInterestRates());

		return response;
	}
	
	@RequestMapping(value = "/mortgage-check", method = RequestMethod.POST)
	public Map<String, Object> checkMortgageFeasibililty(@RequestBody MortgageFeasibilityRequest request) {

		logger.debug("listing interest rates for mortage");
		
		Map<String, Object> response = new HashMap<>();
		response.put(MortgageConstants.MORTGAGE_FEASIBILITY, houseMortgageFeasibilityService.checkMortgageFeasibility(request));

		return response;
	}

}
