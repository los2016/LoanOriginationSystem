package com.myorg.losmodel.model;

import java.io.Serializable;

public final class MortgageDetails implements Serializable{
	
	static final long serialVersionUID = 1L;
	
	@DBEntityMapping(attributeName="PRIMARY_RES_FL")
	private char intentToOccupyAsPrimaryResidence;
	
	@DBEntityMapping(attributeName=Property.LOAN_PROPERTY_DETAILS)
	private Property propertyDetails;
			
	@DBEntityMapping(attributeName=Loan.NEW_LOAN_DETAILS)
	private Loan loanDetails;
		
	@DBEntityMapping(attributeName="REG_W_FL")
	private char isRegWTransaction;
	
	@DBEntityMapping(attributeName="LOAN_TYPE_CD")
	private String loanType; // Purchase, Refinance, Modify a loan
	
	@DBEntityMapping(attributeName="CURRENT_LOAN_BANK_CD")
	private String institutionNameHoldingCurrentLoan;
	
	@DBEntityMapping(attributeName=Loan.REFINANCE_LOAN_DETAILS)
	private Loan refinanceLoanDetails;
	
	@DBEntityMapping(attributeName="EXPECTED_USE_PROCEEDS_CD")
	private String useOfProceeds;
	
	@DBEntityMapping(attributeName="HELOC_PROOCEEDS_TO_PURCHASE_PROP_FL")
	private char isLoanUsedToPurchaseDwelling;
	
	@DBEntityMapping(attributeName="HELOC_PROOCEEDS_TO_PAYOFF_SECURED_LOAN_FL")
	private char isLoanUsedForPayoff;
	
	@DBEntityMapping(attributeName="NEW_LOAN_PRODUCT_CD")
	private String mortgageProductSelection;
	
	@DBEntityMapping(attributeName="EST_PROPERTY_VALUATION_AMOUNT")
	private double estiamtedPropertyValue;
	
	@DBEntityMapping(attributeName="BROKER_INVOLVEMENT_FL")
	private char isMortgageBrokerInvolved;
	
	@DBEntityMapping(attributeName="RECENT_MTG_APPLIED_FL")
	private char appliedForMortgageBefore;
	
	@DBEntityMapping(attributeName="TAX_ESCROW_FL")
	private char isEscrowForRealEstateTaxes;
	
	@DBEntityMapping(attributeName="HOMEOWNERS_INSURANCE_ESCROW_FL")
	private char isEscrowForHomeOwnersInsurance;
	
	@DBEntityMapping(attributeName=Person.KEY_CONTACT)
	private Person keyContactOfThisTransaction;
	
	@DBEntityMapping(attributeName=Person.APRAISAL_CONTACT)
	private Person contactForAppraisal;
	
	private Income income;
	
	@DBEntityMapping(attributeName=Person.BUSINESS_MANAGER_CONTACT)
	private Person marketBusinessManager;
	
	private Person capitalAdvisor;
	
	private Person bankerOrAdvisor;
	
	@DBEntityMapping(attributeName="CAPITAL_ADVISOR_SID_CD")
	private String advisorCostCenter;
		
	@DBEntityMapping(attributeName="MTG_ADVISOR_NMLS_ID")
	private String mortgageAdvisorNMLSId;
	
	
	private Person attorneyDetails;
	
	private Person mortgageAnalyst;
	
	@DBEntityMapping(attributeName="MLO_DIFFERENT_FROM_MTG_ADVISOR_FL")
	private char isMLODiffThanAdvisor;
	
	@DBEntityMapping(attributeName="MLO_LIC_STATUS_CD")
	private char MLOLicenseStatus;
	
	private Person mortgageLoanOriginator;
	
	@DBEntityMapping(attributeName="MLO_NMLS_ID")
	private String MLONMSid;
	
	@DBEntityMapping(attributeName="MLO_STATUS_VALID_FL")
	private char isMLOStatusValidated;
	
	@DBEntityMapping(attributeName="TITLE_HOLD_CD")
	private String titleHoldPlan;
		
	@DBEntityMapping(attributeName=Person.INSURANCE_AGENT_CONTACT)
	private Person insuranceAgent;
	
	@DBEntityMapping(attributeName="NON_JPMC_ASSET_AM")
	private double valueOfNonJPMCAssets;
	
	@DBEntityMapping(attributeName="TOTAL_DEBT_OUTSTANDING_AM")
	private double totalOutandingDebt;
	
	@DBEntityMapping(attributeName="MONTHLY_DEBT_PAYMENT_AM")
	private double monthlyPaymentAgainstDebt;
	
	@DBEntityMapping(attributeName="TRAVEL_FL")
	private char planForTravel;
	
	@DBEntityMapping(attributeName="TRAVEL_FROM_DT")
	private String travelDates;
	
	@DBEntityMapping(attributeName="BORROWER_JPMC_RELATIONSHIP")
	private String borrowerRelationshipToJPMC;
	
	@DBEntityMapping(attributeName="LOAN_GUARANTEE_FL")
	private char loanGurantorPresent;
	
	private byte[] guranteeDocument;
	
	@DBEntityMapping(attributeName="PREV_YR_UNDERWRITING_FL")
	private char lastYearUnderwritingStatus;
	
	@DBEntityMapping(attributeName="POWER_OF_ATTORNEY_FL")
	private char planToUsePOA;
	
	private byte[] POADocument;
	
	@DBEntityMapping(attributeName="THIRD_PARTY_GUARANTOR_CREDIT_FACILITY_FL")
	private char isGurantorForCreditFacilities;
	
	@DBEntityMapping(attributeName="UNSATISFIED_JDGMT_TAX_LIEN_FL")
	private char isSubjectToTaxJudgements;
	
	@DBEntityMapping(attributeName="LAST_ALL_TAX_FILING_YEAR_NO")
	private int lastYearofTaxFiling;
	
	private Person taxPreparer;
	
	@DBEntityMapping(attributeName="IRS_AUDIT_FL")
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
