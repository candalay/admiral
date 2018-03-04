package com.ing.mortgage.service;

import com.ing.mortgage.dto.MortgageFeasibilityRequest;
import com.ing.mortgage.dto.MortgageFeasibilityResponse;

public interface MortgageFeasibilityService {

	MortgageFeasibilityResponse checkMortgageFeasibility(MortgageFeasibilityRequest request);

}
