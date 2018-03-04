package com.ing.mortgage.dto;

public class InterestRateDTO {

	private int maturityPeriod;
	private double interestRate;
	private String lastUpdatedDate;

	public int getMaturityPeriod() {
		return maturityPeriod;
	}

	public void setMaturityPeriod(int maturityPeriod) {
		this.maturityPeriod = maturityPeriod;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

}
