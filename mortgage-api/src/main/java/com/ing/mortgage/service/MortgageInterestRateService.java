package com.ing.mortgage.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ing.mortgage.dto.InterestRateDTO;

@Service("MortgageInterestRateService")
public class MortgageInterestRateService implements InterestRateService {
	
	private static final Logger logger = LoggerFactory.getLogger(MortgageInterestRateService.class);

	@Autowired
	@Qualifier("MortgageInterestRateHandler")
	private InterestRateHandler mortgageInterestRateHandler;

	@Override
	public List<InterestRateDTO> getInterestRates() {	
		
		logger.debug("listing interest rates");
		
		return mortgageInterestRateHandler.getInterestRates();
	}

}
