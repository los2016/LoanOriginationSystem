package com.myorg.losmodel.model;

import java.io.Serializable;

public class Loan implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	public static final String REFINANCE_LOAN_DETAILS = "accountNo=CURRENT_LOAN_AC_NUM_CD|loanAmount=CURRENT_PAYMENT_AM|producType=CURRENT_LOAN_PRODUCT_CD|currency=CURRENT_PAYMENT_CCY_CD|interestRate=CURRENT_INT_RT|purchaseYear=CURRENT_HOME_PURCHASE_YR";
	
	public static final String NEW_LOAN_DETAILS = "loanAmount=LOAN_AM|producType=LOAN_TYPE_CD|currency=LOAN_AM_CCY_CD|interestRate=INTEREST_RT";

	private String accountNo;
	
	private double loanAmount;
	
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

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	
	
}
