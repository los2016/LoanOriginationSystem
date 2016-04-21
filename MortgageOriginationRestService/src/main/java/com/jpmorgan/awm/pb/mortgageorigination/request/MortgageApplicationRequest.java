package com.jpmorgan.awm.pb.mortgageorigination.request;

import com.myorg.losmodel.model.client.MortgageApplication;

public class MortgageApplicationRequest {

	private String saveType;
	private MortgageApplication mortgageApplication;

	public String getSaveType() {
		return saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	public MortgageApplication getMortgageApplication() {
		return mortgageApplication;
	}

	public void setMortgageApplication(MortgageApplication mortgageApplication) {
		this.mortgageApplication = mortgageApplication;
	}

}
