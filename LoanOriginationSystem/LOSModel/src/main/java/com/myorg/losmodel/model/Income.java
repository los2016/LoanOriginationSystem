package com.myorg.losmodel.model;

import java.io.Serializable;
import java.util.List;

public final class Income implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	@DBEntityMapping(attributeName="ANNUAL_INCOME_AM")	
	private double annualIncome;
	
	@DBEntityMapping(attributeName="PRIMARY_INCOME_SRC_CD")
	private List<String> incomeSources;
	
	@DBEntityMapping(attributeName="CONSISTENT_YOY_INCOME_FL")
	private char incomeConsistentOverYears;

	public double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

	public List<String> getIncomeSources() {
		return incomeSources;
	}

	public void setIncomeSources(List<String> incomeSources) {
		this.incomeSources = incomeSources;
	}

	public char getIncomeConsistentOverYears() {
		return incomeConsistentOverYears;
	}

	public void setIncomeConsistentOverYears(char incomeConsistentOverYears) {
		this.incomeConsistentOverYears = incomeConsistentOverYears;
	}
	

}
