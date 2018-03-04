package com.ing.mortgage.dto;

import java.math.BigDecimal;

public class MortgageFeasibilityResponse {

	private Boolean feasibleMortgage;
	private BigDecimal monthlyCost;

	public Boolean getFeasibleMortgage() {
		return feasibleMortgage;
	}

	public void setFeasibleMortgage(Boolean feasibleMortgage) {
		this.feasibleMortgage = feasibleMortgage;
	}

	public BigDecimal getMonthlyCost() {
		return monthlyCost;
	}

	public void setMonthlyCost(BigDecimal monthlyCost) {
		this.monthlyCost = monthlyCost;
	}

}
