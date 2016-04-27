package com.myorg.losmodel.model.questions;

import java.util.List;

public class TimelineRequest {

	protected String languageCd;
	protected String userCode;
	protected List<String> mortgageId;
	
	public String getLanguageCd() {
		return languageCd;
	}
	public List<String> getMortgageId() {
		return mortgageId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setLanguageCd(String languageCd) {
		this.languageCd = languageCd;
	}
	public void setMortgageId(List<String> mortgageId) {
		this.mortgageId = mortgageId;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
}
