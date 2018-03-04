package com.ing.mortgage.dto;

import java.math.BigDecimal;

public class MortgageFeasibilityRequest {

	private Integer maturityPeriod;
	private BigDecimal income;
	private BigDecimal loanValue;
	private BigDecimal homeValue;

	public Integer getMaturityPeriod() {
		return maturityPeriod;
	}

	public void setMaturityPeriod(Integer maturityPeriod) {
		this.maturityPeriod = maturityPeriod;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigDecimal getLoanValue() {
		return loanValue;
	}

	public void setLoanValue(BigDecimal loanValue) {
		this.loanValue = loanValue;
	}

	public BigDecimal getHomeValue() {
		return homeValue;
	}

	public void setHomeValue(BigDecimal homeValue) {
		this.homeValue = homeValue;
	}

}
