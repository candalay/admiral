package com.ing.mortgage.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ing.mortgage.model.InterestRate;

@Repository
public interface InterestRatesRepository extends BaseRepository<InterestRate, Long> {
	
	List<InterestRate> findInterestRateByMaturityPeriod(Integer maturityPeriod);

}
