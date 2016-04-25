package com.jpmorgan.awm.pb.mortgageorigination.response;

import java.util.List;

import com.myorg.losmodel.model.LOSResponse;
import com.myorg.losmodel.model.client.MortgageApplication;

public class MortgageApplicationResponse {

	private List<MortgageApplication> mortgageApplications;
	private LOSResponse response;

	public LOSResponse getResponse() {
		return response;
	}

	public void setResponse(LOSResponse response) {
		this.response = response;
	}

	public List<MortgageApplication> getMortgageApplications() {
		return mortgageApplications;
	}

	public void setMortgageApplications(List<MortgageApplication> mortgageApplications) {
		this.mortgageApplications = mortgageApplications;
	}

}
