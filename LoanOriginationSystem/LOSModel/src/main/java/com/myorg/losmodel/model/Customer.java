package com.myorg.losmodel.model;

import java.util.List;

public abstract class Customer {
	
	private boolean highlySensitive;
	
	private boolean highlyConfidential;
	
	private String country; // country of Primary Domicile
	
	private String state; // state of domicile
	
	private String fullName;	
	
	private String nationality;
	
	private List<String> primaryCountriesOfAssets;
	
	private String uniqueIdentifierType; // SSN, TIN or EIN
	
	private String uniqueIdentifier;
	
	private String clientSponsorName; // internal Banker name
	
	private String existingClientOrProspect; // "C" - Client , "P" - prospect
	
	private char witholdingFederalStatus; // 'Y'
	
	private String primaryIdentification;
	
	private String primaryIdentificationNo;
	
	public boolean isHighlySensitive() {
		return highlySensitive;
	}

	public void setHighlySensitive(boolean highlySensitive) {
		this.highlySensitive = highlySensitive;
	}

	public boolean isHighlyConfidential() {
		return highlyConfidential;
	}

	public void setHighlyConfidential(boolean highlyConfidential) {
		this.highlyConfidential = highlyConfidential;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<String> getPrimaryCountriesOfAssets() {
		return primaryCountriesOfAssets;
	}

	public void setPrimaryCountriesOfAssets(List<String> primaryCountriesOfAssets) {
		this.primaryCountriesOfAssets = primaryCountriesOfAssets;
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

	public String getExistingClientOrProspect() {
		return existingClientOrProspect;
	}

	public void setExistingClientOrProspect(String existingClientOrProspect) {
		this.existingClientOrProspect = existingClientOrProspect;
	}

	public char getWitholdingFederalStatus() {
		return witholdingFederalStatus;
	}

	public void setWitholdingFederalStatus(char witholdingFederalStatus) {
		this.witholdingFederalStatus = witholdingFederalStatus;
	}

	public String getPrimaryIdentification() {
		return primaryIdentification;
	}

	public void setPrimaryIdentification(String primaryIdentification) {
		this.primaryIdentification = primaryIdentification;
	}

	public String getPrimaryIdentificationNo() {
		return primaryIdentificationNo;
	}

	public void setPrimaryIdentificationNo(String primaryIdentificationNo) {
		this.primaryIdentificationNo = primaryIdentificationNo;
	}

	public String getPrimaryIdentificationIssuanceDate() {
		return primaryIdentificationIssuanceDate;
	}

	public void setPrimaryIdentificationIssuanceDate(String primaryIdentificationIssuanceDate) {
		this.primaryIdentificationIssuanceDate = primaryIdentificationIssuanceDate;
	}

	public String getPrimaryIdentificationExpiryDate() {
		return primaryIdentificationExpiryDate;
	}

	public void setPrimaryIdentificationExpiryDate(String primaryIdentificationExpiryDate) {
		this.primaryIdentificationExpiryDate = primaryIdentificationExpiryDate;
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

	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
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

	public char getComakerOrEndoreser() {
		return comakerOrEndoreser;
	}

	public void setComakerOrEndoreser(char comakerOrEndoreser) {
		this.comakerOrEndoreser = comakerOrEndoreser;
	}

	public char getIsJPMSClient() {
		return isJPMSClient;
	}

	public void setIsJPMSClient(char isJPMSClient) {
		this.isJPMSClient = isJPMSClient;
	}

	public char getIsInternationalClient() {
		return isInternationalClient;
	}

	public void setIsInternationalClient(char isInternationalClient) {
		this.isInternationalClient = isInternationalClient;
	}

	private String primaryIdentificationIssuanceDate;
	
	private String primaryIdentificationExpiryDate;
	
	private double netWorth;
	
	private Address legalAddress;

	private Address workAddress;
	
	private Address homeAddress;
	
	private String creditRequestCaptureMode;
	
	private char anyOutstandingJudgements; // 'Y' / 'N'
	
	private char bankruptcyDuringLast7years; // 'Y' / 'N'
	
	private char forclosureDuringLast7years; // 'Y' / 'N'
	
	private char partyToLawsuit; // 'Y' / 'N'
	
	private char obligationToOtherLoans; // 'Y' / 'N'
	
	private char delinquencyOrDefaultStatus; // 'Y' / 'N'
	
	private char isPartOfDownPaymentBorrowed;
	
	private char comakerOrEndoreser;
	
	private char isJPMSClient;
	
	private char isInternationalClient;
	
	
	
}
