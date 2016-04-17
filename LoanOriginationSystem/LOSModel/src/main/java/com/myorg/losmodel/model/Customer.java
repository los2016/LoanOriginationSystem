package com.myorg.losmodel.model;

import java.io.Serializable;

public abstract class Customer implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	private long ECI;
	
	private char isHighlySensitive; // 'Y', 'N'
	
	private char isHighlyConfidential; // 'Y', 'N'
	
	private String fullName;	
	
	private String primaryCountryOfAssets;
	
	private String uniqueIdentifierType; // SSN, TIN or EIN
	
	private String uniqueIdentifier;
	
	private String clientSponsorName; // internal Banker name
	
	private char isClientOrProspect; // "C" - Client , "P" - prospect
	
	private char witholdingFederalStatus; // 'Y'
	
	private double netWorth;
	
	private Address legalAddress;
	
	private String creditRequestCaptureMode;
	
	private char anyOutstandingJudgements; // 'Y' / 'N'
	
	private char bankruptcyDuringLast7years; // 'Y' / 'N'
	
	private char forclosureDuringLast7years; // 'Y' / 'N'
	
	private char partyToLawsuit; // 'Y' / 'N'
	
	private char obligationToOtherLoans; // 'Y' / 'N'
	
	private char delinquencyOrDefaultStatus; // 'Y' / 'N'
	
	private char isPartOfDownPaymentBorrowed; // 'Y' / 'N'
	
	private String sourceOfDownPayment;
	
	private char comakerOrEndoreser;
	
	private char isUSCitizen;  // 'Y' / 'N'
	
	private char isPermenantResident; // 'Y' / 'N'
	
	private char hadOwnershipInterestinProperty;
	
	private String typeOfProperty;
	
	private String howWasTitleHold;
	
	private String whenWasApplicationSubmitted;
	
	private char isJPMSClient;
	
	private char mortgageDocumentMailedToResidence;
	
	private Address homeAddress;
	
	private char isInternationalClient;

	public long getECI() {
		return ECI;
	}

	public void setECI(long eCI) {
		ECI = eCI;
	}

	public char getIsHighlySensitive() {
		return isHighlySensitive;
	}

	public void setIsHighlySensitive(char isHighlySensitive) {
		this.isHighlySensitive = isHighlySensitive;
	}

	public char getIsHighlyConfidential() {
		return isHighlyConfidential;
	}

	public void setIsHighlyConfidential(char isHighlyConfidential) {
		this.isHighlyConfidential = isHighlyConfidential;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPrimaryCountryOfAssets() {
		return primaryCountryOfAssets;
	}

	public void setPrimaryCountryOfAssets(String primaryCountryOfAssets) {
		this.primaryCountryOfAssets = primaryCountryOfAssets;
	}

	public String getUniqueIdentifierType() {
		return uniqueIdentifierType;
	}

	public void setUniqueIdentifierType(String uniqueIdentifierType) {
		this.uniqueIdentifierType = uniqueIdentifierType;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

	public String getClientSponsorName() {
		return clientSponsorName;
	}

	public void setClientSponsorName(String clientSponsorName) {
		this.clientSponsorName = clientSponsorName;
	}

	public char getIsClientOrProspect() {
		return isClientOrProspect;
	}

	public void setIsClientOrProspect(char isClientOrProspect) {
		this.isClientOrProspect = isClientOrProspect;
	}

	public char getWitholdingFederalStatus() {
		return witholdingFederalStatus;
	}

	public void setWitholdingFederalStatus(char witholdingFederalStatus) {
		this.witholdingFederalStatus = witholdingFederalStatus;
	}

	public double getNetWorth() {
		return netWorth;
	}

	public void setNetWorth(double netWorth) {
		this.netWorth = netWorth;
	}

	public Address getLegalAddress() {
		return legalAddress;
	}

	public void setLegalAddress(Address legalAddress) {
		this.legalAddress = legalAddress;
	}

	public String getCreditRequestCaptureMode() {
		return creditRequestCaptureMode;
	}

	public void setCreditRequestCaptureMode(String creditRequestCaptureMode) {
		this.creditRequestCaptureMode = creditRequestCaptureMode;
	}

	public char getAnyOutstandingJudgements() {
		return anyOutstandingJudgements;
	}

	public void setAnyOutstandingJudgements(char anyOutstandingJudgements) {
		this.anyOutstandingJudgements = anyOutstandingJudgements;
	}

	public char getBankruptcyDuringLast7years() {
		return bankruptcyDuringLast7years;
	}

	public void setBankruptcyDuringLast7years(char bankruptcyDuringLast7years) {
		this.bankruptcyDuringLast7years = bankruptcyDuringLast7years;
	}

	public char getForclosureDuringLast7years() {
		return forclosureDuringLast7years;
	}

	public void setForclosureDuringLast7years(char forclosureDuringLast7years) {
		this.forclosureDuringLast7years = forclosureDuringLast7years;
	}

	public char getPartyToLawsuit() {
		return partyToLawsuit;
	}

	public void setPartyToLawsuit(char partyToLawsuit) {
		this.partyToLawsuit = partyToLawsuit;
	}

	public char getObligationToOtherLoans() {
		return obligationToOtherLoans;
	}

	public void setObligationToOtherLoans(char obligationToOtherLoans) {
		this.obligationToOtherLoans = obligationToOtherLoans;
	}

	public char getDelinquencyOrDefaultStatus() {
		return delinquencyOrDefaultStatus;
	}

	public void setDelinquencyOrDefaultStatus(char delinquencyOrDefaultStatus) {
		this.delinquencyOrDefaultStatus = delinquencyOrDefaultStatus;
	}

	public char getIsPartOfDownPaymentBorrowed() {
		return isPartOfDownPaymentBorrowed;
	}

	public void setIsPartOfDownPaymentBorrowed(char isPartOfDownPaymentBorrowed) {
		this.isPartOfDownPaymentBorrowed = isPartOfDownPaymentBorrowed;
	}

	public String getSourceOfDownPayment() {
		return sourceOfDownPayment;
	}

	public void setSourceOfDownPayment(String sourceOfDownPayment) {
		this.sourceOfDownPayment = sourceOfDownPayment;
	}

	public char getComakerOrEndoreser() {
		return comakerOrEndoreser;
	}

	public void setComakerOrEndoreser(char comakerOrEndoreser) {
		this.comakerOrEndoreser = comakerOrEndoreser;
	}

	public char getIsUSCitizen() {
		return isUSCitizen;
	}

	public void setIsUSCitizen(char isUSCitizen) {
		this.isUSCitizen = isUSCitizen;
	}

	public char getIsPermenantResident() {
		return isPermenantResident;
	}

	public void setIsPermenantResident(char isPermenantResident) {
		this.isPermenantResident = isPermenantResident;
	}

	public char getHadOwnershipInterestinProperty() {
		return hadOwnershipInterestinProperty;
	}

	public void setHadOwnershipInterestinProperty(char hadOwnershipInterestinProperty) {
		this.hadOwnershipInterestinProperty = hadOwnershipInterestinProperty;
	}

	public String getTypeOfProperty() {
		return typeOfProperty;
	}

	public void setTypeOfProperty(String typeOfProperty) {
		this.typeOfProperty = typeOfProperty;
	}

	public String getHowWasTitleHold() {
		return howWasTitleHold;
	}

	public void setHowWasTitleHold(String howWasTitleHold) {
		this.howWasTitleHold = howWasTitleHold;
	}

	public String getWhenWasApplicationSubmitted() {
		return whenWasApplicationSubmitted;
	}

	public void setWhenWasApplicationSubmitted(String whenWasApplicationSubmitted) {
		this.whenWasApplicationSubmitted = whenWasApplicationSubmitted;
	}

	public char getIsJPMSClient() {
		return isJPMSClient;
	}

	public void setIsJPMSClient(char isJPMSClient) {
		this.isJPMSClient = isJPMSClient;
	}

	public char getMortgageDocumentMailedToResidence() {
		return mortgageDocumentMailedToResidence;
	}

	public void setMortgageDocumentMailedToResidence(char mortgageDocumentMailedToResidence) {
		this.mortgageDocumentMailedToResidence = mortgageDocumentMailedToResidence;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public char getIsInternationalClient() {
		return isInternationalClient;
	}

	public void setIsInternationalClient(char isInternationalClient) {
		this.isInternationalClient = isInternationalClient;
	}
	
}
