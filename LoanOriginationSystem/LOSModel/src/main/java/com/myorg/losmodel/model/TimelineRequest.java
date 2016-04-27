package com.myorg.losmodel.model;

import java.util.List;

public class TimelineRequest extends LOSRequest {

	protected String languageCd;
	protected List<String> mortgageId;
	protected String partyId;
	public String getLanguageCd() {
		return languageCd;
	}
	public List<String> getMortgageId() {
		return mortgageId;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setLanguageCd(String languageCd) {
		this.languageCd = languageCd;
	}
	public void setMortgageId(List<String> mortgageId) {
		this.mortgageId = mortgageId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	
}
