package com.myorg.losmodel.model;

import java.io.Serializable;

public final class MortgageDetails implements Serializable{
	
	static final long serialVersionUID = 1L;
	
	private char intentToOccupyAsPrimaryResidence;
	
	private Property propertyDetails;
			
	private Loan loanDetails;
		
	private char isRegWTransaction;
	
	private String loanType; // Purchase, Refinance, Modify a loan
	
	private String institutionNameHoldingCurrentLoan;
	
	private Loan refinanceLoanDetails;
	
	private String useOfProceeds;
	
	private char isLoanUsedToPurchaseDwelling;
	
	private char isLoanUsedForPayoff;
	
	private String mortgageProductSelection;
	
	private double estiamtedPropertyValue;
	
	private char isMortgageBrokerInvolved;
	
	private char appliedForMortgageBefore;
	
	private char isEscrowForRealEstateTaxes;
	
	private char isEscrowForHomeOwnersInsurance;
	
	private Person keyContactOfThisTransaction;
	
	private Person contactForAppraisal;
	
	private Income income;
	
	private Person marketBusinessManager;
	
	private Person capitalAdvisor;
	
	private Person bankerOrAdvisor;
	
	private String advisorCostCenter;
		
	private String mortgageAdvisorNMLSId;
	
	private Person attorneyDetails;
	
	private Person mortgageAnalyst;
	
	private char isMLODiffThanAdvisor;
	
	private char MLOLicenseStatus;
	
	private Person mortgageLoanOriginator;
	
	private String MLONMSid;
	
	private char isMLOStatusValidated;
	
	private String titleHoldPlan;
		
	private Person insuranceAgent;
	
	private double valueOfNonJPMCAssets;
	
	private double totalOutandingDebt;
	
	private double monthlyPaymentAgainstDebt;
	
	private char planForTravel;
	
	private String travelDates;
	
	private String borrowerRelationshipToJPMC;
	
	private char loanGurantorPresent;
	
	private byte[] guranteeDocument;
	
	private char lastYearUnderwritingStatus;
	
	private char planToUsePOA;
	
	private byte[] POADocument;
	
	private char isGurantorForCreditFacilities;
	
	private char isSubjectToTaxJudgements;
	
	private int lastYearofTaxFiling;
	
	private Person taxPreparer;
	
	private char isReturnAudited;

	public char getIntentToOccupyAsPrimaryResidence() {
		return intentToOccupyAsPrimaryResidence;
	}

	public void setIntentToOccupyAsPrimaryResidence(char intentToOccupyAsPrimaryResidence) {
		this.intentToOccupyAsPrimaryResidence = intentToOccupyAsPrimaryResidence;
	}

	public Property getPropertyDetails() {
		return propertyDetails;
	}

	public void setPropertyDetails(Property propertyDetails) {
		this.propertyDetails = propertyDetails;
	}

	
	public Loan getLoanDetails() {
		return loanDetails;
	}

	public void setLoanDetails(Loan loanDetails) {
		this.loanDetails = loanDetails;
	}

	public char isRegWTransaction() {
		return isRegWTransaction;
	}

	public void setRegWTransaction(char isRegWTransaction) {
		this.isRegWTransaction = isRegWTransaction;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getInstitutionNameHoldingCurrentLoan() {
		return institutionNameHoldingCurrentLoan;
	}

	public void setInstitutionNameHoldingCurrentLoan(String institutionNameHoldingCurrentLoan) {
		this.institutionNameHoldingCurrentLoan = institutionNameHoldingCurrentLoan;
	}

	public Loan getRefinanceLoanDetails() {
		return refinanceLoanDetails;
	}

	public void setRefinanceLoanDetails(Loan refinanceLoanDetails) {
		this.refinanceLoanDetails = refinanceLoanDetails;
	}

	public String getUseOfProceeds() {
		return useOfProceeds;
	}

	public void setUseOfProceeds(String useOfProceeds) {
		this.useOfProceeds = useOfProceeds;
	}

	public char getIsLoanUsedToPurchaseDwelling() {
		return isLoanUsedToPurchaseDwelling;
	}

	public void setIsLoanUsedToPurchaseDwelling(char isLoanUsedToPurchaseDwelling) {
		this.isLoanUsedToPurchaseDwelling = isLoanUsedToPurchaseDwelling;
	}

	public char getIsLoanUsedForPayoff() {
		return isLoanUsedForPayoff;
	}

	public void setIsLoanUsedForPayoff(char isLoanUsedForPayoff) {
		this.isLoanUsedForPayoff = isLoanUsedForPayoff;
	}

	public String getMortgageProductSelection() {
		return mortgageProductSelection;
	}

	public void setMortgageProductSelection(String mortgageProductSelection) {
		this.mortgageProductSelection = mortgageProductSelection;
	}

	public double getEstiamtedPropertyValue() {
		return estiamtedPropertyValue;
	}

	public void setEstiamtedPropertyValue(double estiamtedPropertyValue) {
		this.estiamtedPropertyValue = estiamtedPropertyValue;
	}

	public char getIsMortgageBrokerInvolved() {
		return isMortgageBrokerInvolved;
	}

	public void setIsMortgageBrokerInvolved(char isMortgageBrokerInvolved) {
		this.isMortgageBrokerInvolved = isMortgageBrokerInvolved;
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

	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}

	public Person getMarketBusinessManager() {
		return marketBusinessManager;
	}

	public void setMarketBusinessManager(Person marketBusinessManager) {
		this.marketBusinessManager = marketBusinessManager;
	}

	public Person getCapitalAdvisor() {
		return capitalAdvisor;
	}

	public void setCapitalAdvisor(Person capitalAdvisor) {
		this.capitalAdvisor = capitalAdvisor;
	}

	public Person getBankerOrAdvisor() {
		return bankerOrAdvisor;
	}

	public void setBankerOrAdvisor(Person bankerOrAdvisor) {
		this.bankerOrAdvisor = bankerOrAdvisor;
	}

	public String getAdvisorCostCenter() {
		return advisorCostCenter;
	}

	public void setAdvisorCostCenter(String advisorCostCenter) {
		this.advisorCostCenter = advisorCostCenter;
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

	public Person getMortgageAnalyst() {
		return mortgageAnalyst;
	}

	public void setMortgageAnalyst(Person mortgageAnalyst) {
		this.mortgageAnalyst = mortgageAnalyst;
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

	public Person getMortgageLoanOriginator() {
		return mortgageLoanOriginator;
	}

	public void setMortgageLoanOriginator(Person mortgageLoanOriginator) {
		this.mortgageLoanOriginator = mortgageLoanOriginator;
	}

	public String getMLONMSid() {
		return MLONMSid;
	}

	public void setMLONMSid(String mLONMSid) {
		MLONMSid = mLONMSid;
	}

	public char getIsMLOStatusValidated() {
		return isMLOStatusValidated;
	}

	public void setIsMLOStatusValidated(char isMLOStatusValidated) {
		this.isMLOStatusValidated = isMLOStatusValidated;
	}

	public String getTitleHoldPlan() {
		return titleHoldPlan;
	}

	public void setTitleHoldPlan(String titleHoldPlan) {
		this.titleHoldPlan = titleHoldPlan;
	}

	public Person getInsuranceAgent() {
		return insuranceAgent;
	}

	public void setInsuranceAgent(Person insuranceAgent) {
		this.insuranceAgent = insuranceAgent;
	}

	public double getValueOfNonJPMCAssets() {
		return valueOfNonJPMCAssets;
	}

	public void setValueOfNonJPMCAssets(double valueOfNonJPMCAssets) {
		this.valueOfNonJPMCAssets = valueOfNonJPMCAssets;
	}

	public double getTotalOutandingDebt() {
		return totalOutandingDebt;
	}

	public void setTotalOutandingDebt(double totalOutandingDebt) {
		this.totalOutandingDebt = totalOutandingDebt;
	}

	public double getMonthlyPaymentAgainstDebt() {
		return monthlyPaymentAgainstDebt;
	}

	public void setMonthlyPaymentAgainstDebt(double monthlyPaymentAgainstDebt) {
		this.monthlyPaymentAgainstDebt = monthlyPaymentAgainstDebt;
	}

	public char getPlanForTravel() {
		return planForTravel;
	}

	public void setPlanForTravel(char planForTravel) {
		this.planForTravel = planForTravel;
	}

	public String getTravelDates() {
		return travelDates;
	}

	public void setTravelDates(String travelDates) {
		this.travelDates = travelDates;
	}

	public String getBorrowerRelationshipToJPMC() {
		return borrowerRelationshipToJPMC;
	}

	public void setBorrowerRelationshipToJPMC(String borrowerRelationshipToJPMC) {
		this.borrowerRelationshipToJPMC = borrowerRelationshipToJPMC;
	}

	public char getLoanGurantorPresent() {
		return loanGurantorPresent;
	}

	public void setLoanGurantorPresent(char loanGurantorPresent) {
		this.loanGurantorPresent = loanGurantorPresent;
	}

	public byte[] getGuranteeDocument() {
		return guranteeDocument;
	}

	public void setGuranteeDocument(byte[] guranteeDocument) {
		this.guranteeDocument = guranteeDocument;
	}

	public char getLastYearUnderwritingStatus() {
		return lastYearUnderwritingStatus;
	}

	public void setLastYearUnderwritingStatus(char lastYearUnderwritingStatus) {
		this.lastYearUnderwritingStatus = lastYearUnderwritingStatus;
	}

	public char getPlanToUsePOA() {
		return planToUsePOA;
	}

	public void setPlanToUsePOA(char planToUsePOA) {
		this.planToUsePOA = planToUsePOA;
	}

	public byte[] getPOADocument() {
		return POADocument;
	}

	public void setPOADocument(byte[] pOADocument) {
		POADocument = pOADocument;
	}

	public char getIsGurantorForCreditFacilities() {
		return isGurantorForCreditFacilities;
	}

	public void setIsGurantorForCreditFacilities(char isGurantorForCreditFacilities) {
		this.isGurantorForCreditFacilities = isGurantorForCreditFacilities;
	}

	public char getIsSubjectToTaxJudgements() {
		return isSubjectToTaxJudgements;
	}

	public void setIsSubjectToTaxJudgements(char isSubjectToTaxJudgements) {
		this.isSubjectToTaxJudgements = isSubjectToTaxJudgements;
	}

	public int getLastYearofTaxFiling() {
		return lastYearofTaxFiling;
	}

	public void setLastYearofTaxFiling(int lastYearofTaxFiling) {
		this.lastYearofTaxFiling = lastYearofTaxFiling;
	}

	public Person getTaxPreparer() {
		return taxPreparer;
	}

	public void setTaxPreparer(Person taxPreparer) {
		this.taxPreparer = taxPreparer;
	}

	public char getIsReturnAudited() {
		return isReturnAudited;
	}

	public void setIsReturnAudited(char isReturnAudited) {
		this.isReturnAudited = isReturnAudited;
	}
}
