package com.jpmorgan.awm.pb.mortgageorigination.response;

import com.myorg.losmodel.model.LOSResponse;
import com.myorg.losmodel.model.client.MortgageApplication;

public class MortgageApplicationResponse {

	private long mortgageId;
	private MortgageApplication mortgageApplication;
	private LOSResponse response;

	public LOSResponse getResponse() {
		return response;
	}

	public void setResponse(LOSResponse response) {
		this.response = response;
	}

	public long getMortgageId() {
		return mortgageId;
	}

	public void setMortgageId(long mortgageId) {
		this.mortgageId = mortgageId;
	}

	public MortgageApplication getMortgageApplication() {
		return mortgageApplication;
	}

	public void setMortgageApplication(MortgageApplication mortgageApplication) {
		this.mortgageApplication = mortgageApplication;
	}

}
