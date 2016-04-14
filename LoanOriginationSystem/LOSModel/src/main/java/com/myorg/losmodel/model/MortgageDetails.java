package com.myorg.losmodel.model; 

public class MortgageDetails {
		
	private double estiamtedPropertyValue;
	
	private double loanAmount;
	
	private float interestRate;
	
	public double getEstiamtedPropertyValue() {
		return estiamtedPropertyValue;
	}

	public void setEstiamtedPropertyValue(double estiamtedPropertyValue) {
		this.estiamtedPropertyValue = estiamtedPropertyValue;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public boolean isRegWTransaction() {
		return isRegWTransaction;
	}

	public void setRegWTransaction(boolean isRegWTransaction) {
		this.isRegWTransaction = isRegWTransaction;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public char getIsMortgageBrokerInvolved() {
		return isMortgageBrokerInvolved;
	}

	public void setIsMortgageBrokerInvolved(char isMortgageBrokerInvolved) {
		this.isMortgageBrokerInvolved = isMortgageBrokerInvolved;
	}

	public char getIsInvolvedInActiveMilitaryService() {
		return isInvolvedInActiveMilitaryService;
	}

	public void setIsInvolvedInActiveMilitaryService(char isInvolvedInActiveMilitaryService) {
		this.isInvolvedInActiveMilitaryService = isInvolvedInActiveMilitaryService;
	}

	public Property getPropertyDetails() {
		return propertyDetails;
	}

	public void setPropertyDetails(Property propertyDetails) {
		this.propertyDetails = propertyDetails;
	}

	public char getAppliedForMortgageBefore() {
		return appliedForMortgageBefore;
	}

	public void setAppliedForMortgageBefore(char appliedForMortgageBefore) {
		this.appliedForMortgageBefore = appliedForMortgageBefore;
	}

	public char getIsEscrowForRealEstateTaxes() {
		return isEscrowForRealEstateTaxes;
	}

	public void setIsEscrowForRealEstateTaxes(char isEscrowForRealEstateTaxes) {
		this.isEscrowForRealEstateTaxes = isEscrowForRealEstateTaxes;
	}

	public char getIsEscrowForHomeOwnersInsurance() {
		return isEscrowForHomeOwnersInsurance;
	}

	public void setIsEscrowForHomeOwnersInsurance(char isEscrowForHomeOwnersInsurance) {
		this.isEscrowForHomeOwnersInsurance = isEscrowForHomeOwnersInsurance;
	}

	public Person getKeyContactOfThisTransaction() {
		return keyContactOfThisTransaction;
	}

	public void setKeyContactOfThisTransaction(Person keyContactOfThisTransaction) {
		this.keyContactOfThisTransaction = keyContactOfThisTransaction;
	}

	public Person getContactForAppraisal() {
		return contactForAppraisal;
	}

	public void setContactForAppraisal(Person contactForAppraisal) {
		this.contactForAppraisal = contactForAppraisal;
	}

	public String getMarketBusinessManagerName() {
		return marketBusinessManagerName;
	}

	public void setMarketBusinessManagerName(String marketBusinessManagerName) {
		this.marketBusinessManagerName = marketBusinessManagerName;
	}

	public String getCapitalAdvisorName() {
		return capitalAdvisorName;
	}

	public void setCapitalAdvisorName(String capitalAdvisorName) {
		this.capitalAdvisorName = capitalAdvisorName;
	}

	public String getMortgageAdvisorName() {
		return mortgageAdvisorName;
	}

	public void setMortgageAdvisorName(String mortgageAdvisorName) {
		this.mortgageAdvisorName = mortgageAdvisorName;
	}

	public String getMortgageAdvisorNMLSId() {
		return mortgageAdvisorNMLSId;
	}

	public void setMortgageAdvisorNMLSId(String mortgageAdvisorNMLSId) {
		this.mortgageAdvisorNMLSId = mortgageAdvisorNMLSId;
	}

	public Person getAttorneyDetails() {
		return attorneyDetails;
	}

	public void setAttorneyDetails(Person attorneyDetails) {
		this.attorneyDetails = attorneyDetails;
	}

	public String getAdvisorPhone() {
		return advisorPhone;
	}

	public void setAdvisorPhone(String advisorPhone) {
		this.advisorPhone = advisorPhone;
	}

	public String getAdvisorCostCenter() {
		return advisorCostCenter;
	}

	public void setAdvisorCostCenter(String advisorCostCenter) {
		this.advisorCostCenter = advisorCostCenter;
	}

	public String getMortgageAnalystName() {
		return mortgageAnalystName;
	}

	public void setMortgageAnalystName(String mortgageAnalystName) {
		this.mortgageAnalystName = mortgageAnalystName;
	}

	public char getIsMLODiffThanAdvisor() {
		return isMLODiffThanAdvisor;
	}

	public void setIsMLODiffThanAdvisor(char isMLODiffThanAdvisor) {
		this.isMLODiffThanAdvisor = isMLODiffThanAdvisor;
	}

	public char getMLOLicenseStatus() {
		return MLOLicenseStatus;
	}

	public void setMLOLicenseStatus(char mLOLicenseStatus) {
		MLOLicenseStatus = mLOLicenseStatus;
	}

	private boolean isRegWTransaction;
	
	private String loanType; // Purchase, Refinance, Modify a loan
	
	private char isMortgageBrokerInvolved;
	
	private char isInvolvedInActiveMilitaryService;
	
	private Property propertyDetails;
	
	private char appliedForMortgageBefore;
	
	private char isEscrowForRealEstateTaxes;
	
	private char isEscrowForHomeOwnersInsurance;
	
	private Person keyContactOfThisTransaction;
	
	private Person contactForAppraisal;
	
	private String marketBusinessManagerName;
	
	private String capitalAdvisorName;
	
	private String mortgageAdvisorName;
	
	private String mortgageAdvisorNMLSId;
	
	private Person attorneyDetails;
	
	private String advisorPhone;
	
	private String advisorCostCenter;
	
	private String mortgageAnalystName;
	
	private char isMLODiffThanAdvisor;
	
	private char MLOLicenseStatus;		
}
