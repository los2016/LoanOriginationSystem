package com.myorg.losmodel.model;

import java.io.Serializable;

import com.myorg.losmodel.model.DBEntityMapping;

public abstract class Customer implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	@DBEntityMapping(attributeName = "ECI_CD")
	private long ECI;
	
	@DBEntityMapping(attributeName = "HIGH_SENSITIVE_FL")
	private char isHighlySensitive; // 'Y', 'N'
	
	@DBEntityMapping(attributeName = "CONFIDENTIAL_NAME_FL")
	private char isHighlyConfidential; // 'Y', 'N'
	
	@DBEntityMapping(attributeName = Person.CLIENT_NAME)
	private Person fullName;	
	
	@DBEntityMapping(attributeName = "COUNTRY_OF_ASSET_ISO2_CD")
	private String primaryCountryOfAssets;
	
	private String uniqueIdentifierType; // SSN, TIN or EIN
	
	@DBEntityMapping(attributeName = "TAX_IDENTIFICATION_DESC")
	private String uniqueIdentifier;
	
	@DBEntityMapping(attributeName = "SPONSOR_SID_CD")
	private String clientSponsorName; // internal Banker name
	
	@DBEntityMapping(attributeName = "CLIENT_PROSPECT_FL")
	private char isClientOrProspect; // "C" - Client , "P" - prospect
	
	@DBEntityMapping(attributeName = "WITHHOLD_FED_STATUS_CD")
	private char witholdingFederalStatus; // 'Y'
	
	@DBEntityMapping(attributeName = "NET_WORTH_CCY_CD")
	private double netWorth;
	
	@DBEntityMapping(attributeName=Address.MAILING_ADDRESS)
	private Address legalAddress;
	
	@DBEntityMapping(attributeName = "MODE_OF_REQUEST_CD")
	private String creditRequestCaptureMode;
	
	@DBEntityMapping(attributeName = "OUTSTANDING_JUDDGEMENT_FL")
	private char anyOutstandingJudgements; // 'Y' / 'N'
	
	@DBEntityMapping(attributeName = "BANKRUPT_FL")
	private char bankruptcyDuringLast7years; // 'Y' / 'N'
	
	@DBEntityMapping(attributeName = "FORECLOSURE_FL")
	private char forclosureDuringLast7years; // 'Y' / 'N'
	
	@DBEntityMapping(attributeName = "PARTY_TO_LAWSUIT_FL")
	private char partyToLawsuit; // 'Y' / 'N'
	
	@DBEntityMapping(attributeName = "OBLIGOR_FL")
	private char obligationToOtherLoans; // 'Y' / 'N'
	
	@DBEntityMapping(attributeName = "DEFAULTED_FL")
	private char delinquencyOrDefaultStatus; // 'Y' / 'N'
	
	@DBEntityMapping(attributeName = "BORROWED_DOWNPAYMENT_FL")
	private char isPartOfDownPaymentBorrowed; // 'Y' / 'N'
	
	@DBEntityMapping(attributeName = "DOWNPMT_SRC_CD")
	private String sourceOfDownPayment;
	
	@DBEntityMapping(attributeName = "NOTE_ENDORSE_COMAKER_FL")
	private char comakerOrEndoreser;
	
	@DBEntityMapping(attributeName = "US_CITIZEN_FL")
	private char isUSCitizen;  // 'Y' / 'N'
	
	@DBEntityMapping(attributeName = "US_RES_ALIEN_FL")
	private char isPermenantResident; // 'Y' / 'N'
	
	@DBEntityMapping(attributeName = "OWNERSHIP_INTEREST_FL")
	private char hadOwnershipInterestinProperty;
	
	@DBEntityMapping(attributeName = "CURRENT_PROPERTY_OWNERSHIP_CD")
	private String typeOfProperty;
	
	@DBEntityMapping(attributeName = "CURRENT_PROPERTY_OWNERSHIP_TYPE_CD")
	private String howWasTitleHold;
	
	@DBEntityMapping(attributeName = "APPLICATION_SUBMITTED_TS")
	private String whenWasApplicationSubmitted;
	
	@DBEntityMapping(attributeName = "JPMS_CLIENT_FL")
	private char isJPMSClient;
	
	@DBEntityMapping(attributeName = "MTG_DOC_MAIL_DIFF_ADDRESS_FL")
	private char mortgageDocumentMailedToResidence;
	
	@DBEntityMapping(attributeName=Address.HOME_ADDRESS)
	private Address homeAddress;
	
	@DBEntityMapping(attributeName = "INTERNATIONAL_CLIENT_FL")
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

	public Person getFullName() {
		return fullName;
	}

	public void setFullName(Person fullName) {
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
