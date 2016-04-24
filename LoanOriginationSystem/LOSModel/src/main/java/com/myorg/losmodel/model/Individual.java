package com.myorg.losmodel.model;

import java.util.List;

public abstract class Individual extends Customer {
	
	static final long serialVersionUID = 1L;
	
	@DBEntityMapping(attributeName="COUNTRY_OF_NATIONALITY_ISO2_CD")
	private String nationality;
	
	@DBEntityMapping(attributeName="EMPLOYER_NM")
	private String employerName;
	
	@DBEntityMapping(attributeName="OCCUPATION_NM")
	private String occupation;
	
	@DBEntityMapping(attributeName="JOB_TITLE_NM")
	private String jobTitle;
	
	@DBEntityMapping(attributeName="YEARS_IN_OCCUPATION_NO")
	private int yearsInOccupation;
	
	private byte[] employmentVerificationDocument;
	
	private byte[] payStub;
	
	@DBEntityMapping(attributeName="MARITAL_STATUS_CD")
	private String martialStatus;
	
	@DBEntityMapping(attributeName="ALIMONY_PAY_OBLIGATION_FL")
	private char obligationToSupport;
	
	private byte[] divorceDecree;
	
	@DBEntityMapping(attributeName="INCLUDE_ALIMONY_FL")
	private char divorceIncomeFlag;
	
	@DBEntityMapping(attributeName="INCLUDE_SPOUSE_COBORROWER_FL")
	private char isSpouseBorrower;
	
	@DBEntityMapping(attributeName="GENDER_CD")
	private String gender;
	
	@DBEntityMapping(attributeName="BIRTH_DT")
	private String dateOfBirth;
	
	@DBEntityMapping(attributeName=Address.WORK_ADDRESS)
	private Address workAddress;
	
	@DBEntityMapping(attributeName="ETHNICITY_CD")
	private String ethinicity;	
	
	@DBEntityMapping(attributeName="RACE_CD")
	private List<String> race;
	
	@DBEntityMapping(attributeName="EMPLOYEE_CLIENT_FL")
	private char isJPMCEmployee;
	
	@DBEntityMapping(attributeName="FIRSTTIME_HOMEBUYER_FL")
	private char isFirstTimeHomeBuyer;
	
	@DBEntityMapping(attributeName="REG_O_OFFICER_FL")
	private char isRegOOfficer;
	
	@DBEntityMapping(attributeName="CURRENT_RES_STATUS_CD")
	private String residencyStatus;
	
	@DBEntityMapping(attributeName="NO_OF_YRS_AT_CURR_RES_DESC")
	private int noOfYearsAtThisResidence;
	
	@DBEntityMapping(attributeName="MILITARY_FL")
	private char inActiveMilitaryService;
	
	@DBEntityMapping(attributeName="GENERAL_PARTNER_FL")
	private char generalPartnerinPartnership;
	
	@DBEntityMapping(attributeName="PUBLIC_STOCK_CONTROL_FL")
	private char isPublicStockOwner;
	
	@DBEntityMapping(attributeName="EXECUTED_WILL_FL")
	private char isExecutedWillPresent;
	
	@DBEntityMapping(attributeName="OWN_LIFE_INSURANCE_FL")
	private char ownLifeInsurance;
		
	
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public int getYearsInOccupation() {
		return yearsInOccupation;
	}

	public void setYearsInOccupation(int yearsInOccupation) {
		this.yearsInOccupation = yearsInOccupation;
	}

	public byte[] getEmploymentVerificationDocument() {
		return employmentVerificationDocument;
	}

	public void setEmploymentVerificationDocument(byte[] employmentVerificationDocument) {
		this.employmentVerificationDocument = employmentVerificationDocument;
	}

	public byte[] getPayStub() {
		return payStub;
	}

	public void setPayStub(byte[] payStub) {
		this.payStub = payStub;
	}

	public char getObligationToSupport() {
		return obligationToSupport;
	}

	public void setObligationToSupport(char obligationToSupport) {
		this.obligationToSupport = obligationToSupport;
	}

	public byte[] getDivorceDecree() {
		return divorceDecree;
	}

	public void setDivorceDecree(byte[] divorceDecree) {
		this.divorceDecree = divorceDecree;
	}

	public char getDivorceIncomeFlag() {
		return divorceIncomeFlag;
	}

	public void setDivorceIncomeFlag(char divorceIncomeFlag) {
		this.divorceIncomeFlag = divorceIncomeFlag;
	}

	public char getIsSpouseBorrower() {
		return isSpouseBorrower;
	}

	public void setIsSpouseBorrower(char isSpouseBorrower) {
		this.isSpouseBorrower = isSpouseBorrower;
	}

	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
	}

	public int getNoOfYearsAtThisResidence() {
		return noOfYearsAtThisResidence;
	}

	public void setNoOfYearsAtThisResidence(int noOfYearsAtThisResidence) {
		this.noOfYearsAtThisResidence = noOfYearsAtThisResidence;
	}

	public char getInActiveMilitaryService() {
		return inActiveMilitaryService;
	}

	public void setInActiveMilitaryService(char inActiveMilitaryService) {
		this.inActiveMilitaryService = inActiveMilitaryService;
	}

	public char getGeneralPartnerinPartnership() {
		return generalPartnerinPartnership;
	}

	public void setGeneralPartnerinPartnership(char generalPartnerinPartnership) {
		this.generalPartnerinPartnership = generalPartnerinPartnership;
	}

	public char getIsPublicStockOwner() {
		return isPublicStockOwner;
	}

	public void setIsPublicStockOwner(char isPublicStockOwner) {
		this.isPublicStockOwner = isPublicStockOwner;
	}

	public char getIsExecutedWillPresent() {
		return isExecutedWillPresent;
	}

	public void setIsExecutedWillPresent(char isExecutedWillPresent) {
		this.isExecutedWillPresent = isExecutedWillPresent;
	}

	public char getOwnLifeInsurance() {
		return ownLifeInsurance;
	}

	public void setOwnLifeInsurance(char ownLifeInsurance) {
		this.ownLifeInsurance = ownLifeInsurance;
	}

	private double annualIncome;	
	
	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getEthinicity() {
		return ethinicity;
	}

	public void setEthinicity(String ethinicity) {
		this.ethinicity = ethinicity;
	}

	public List<String> getRace() {
		return race;
	}

	public void setRace(List<String> race) {
		this.race = race;
	}

	

	public char getIsJPMCEmployee() {
		return isJPMCEmployee;
	}

	public void setIsJPMCEmployee(char isJPMCEmployee) {
		this.isJPMCEmployee = isJPMCEmployee;
	}

	public char getIsFirstTimeHomeBuyer() {
		return isFirstTimeHomeBuyer;
	}

	public void setIsFirstTimeHomeBuyer(char isFirstTimeHomeBuyer) {
		this.isFirstTimeHomeBuyer = isFirstTimeHomeBuyer;
	}

	public char getIsRegOOfficer() {
		return isRegOOfficer;
	}

	public void setIsRegOOfficer(char isRegOOfficer) {
		this.isRegOOfficer = isRegOOfficer;
	}

	public String getResidencyStatus() {
		return residencyStatus;
	}

	public void setResidencyStatus(String residencyStatus) {
		this.residencyStatus = residencyStatus;
	}

	public double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}


}
