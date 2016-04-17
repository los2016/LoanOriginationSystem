package com.myorg.losmodel.model;

import java.io.Serializable;
import java.util.List;

public final class Income implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	private double annualIncome;
	
	private List<String> incomeSources;
	
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
