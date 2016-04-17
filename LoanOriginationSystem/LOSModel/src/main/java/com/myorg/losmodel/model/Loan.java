package com.myorg.losmodel.model;

import java.io.Serializable;

public class Loan implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	private String accountNo;
	
	private String producType;
	
	private String currency;
	
	private float interestRate;
	
	private int purchaseYear;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getProducType() {
		return producType;
	}

	public void setProducType(String producType) {
		this.producType = producType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public int getPurchaseYear() {
		return purchaseYear;
	}

	public void setPurchaseYear(int purchaseYear) {
		this.purchaseYear = purchaseYear;
	}
	
	
}
