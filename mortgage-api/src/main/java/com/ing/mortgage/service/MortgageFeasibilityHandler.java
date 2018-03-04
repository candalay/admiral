package com.ing.mortgage.service;

import com.ing.mortgage.dto.MortgageFeasibilityRequest;
import com.ing.mortgage.dto.MortgageFeasibilityResponse;

public interface MortgageFeasibilityHandler {

	MortgageFeasibilityResponse calculateFeasibility(MortgageFeasibilityRequest request);

}
