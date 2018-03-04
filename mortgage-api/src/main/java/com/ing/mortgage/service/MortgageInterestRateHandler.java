package com.ing.mortgage.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.mortgage.MortgageUtil;
import com.ing.mortgage.dto.InterestRateDTO;
import com.ing.mortgage.model.InterestRate;
import com.ing.mortgage.repository.InterestRatesRepository;

@Service("MortgageInterestRateHandler")
public class MortgageInterestRateHandler implements InterestRateHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(MortgageInterestRateHandler.class);

	@Autowired
	InterestRatesRepository interestRatesRepository;

	@Override
	public List<InterestRateDTO> getInterestRates() {
		
		logger.debug("querying interest rates");

		List<InterestRate> interestRates = interestRatesRepository.findAll();

		return interestRates.stream().map(interestRate -> convertInterestRatesToDto(interestRate)).collect(Collectors.toList());
	}

	private InterestRateDTO convertInterestRatesToDto(InterestRate interestRate) {
		
		InterestRateDTO interestRateDTO = new InterestRateDTO();
		
		interestRateDTO.setInterestRate(interestRate.getRate());
		interestRateDTO.setMaturityPeriod(interestRate.getMaturityPeriod());
		
		Timestamp formattedDate = MortgageUtil.getDateAsTimeStamp(interestRate.getLastUpdateDate());
		
		interestRateDTO.setLastUpdatedDate(formattedDate.toString());
		
		return interestRateDTO;
	}

}
