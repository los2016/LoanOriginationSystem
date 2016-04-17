package com.myorg.losmodel.model.client;

import com.myorg.losmodel.model.Entity;
import com.myorg.losmodel.model.MortgageDetails;

public final class PBEntityClient extends Entity {
	
	static final long serialVersionUID = 1L;
	
	private MortgageDetails mortgageDetails;

	public MortgageDetails getMortgageDetails() {
		return mortgageDetails;
	}

	public void setMortgageDetails(MortgageDetails mortgageDetails) {
		this.mortgageDetails = mortgageDetails;
	}
		
}