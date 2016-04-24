package com.myorg.losmodel.model;

import com.myorg.losmodel.model.client.MortgageApplication;

public class ValidateQuestionRequest extends LOSRequest {	
	
	private Integer questionId;
	private MortgageApplication mortgageApplication;
	
	
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public MortgageApplication getMortgageApplication() {
		return mortgageApplication;
	}
	public void setMortgageApplication(MortgageApplication mortgageApplication) {
		this.mortgageApplication = mortgageApplication;
	}	

}
