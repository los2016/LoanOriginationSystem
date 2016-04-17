package com.myorg.losmodel.model;

import java.util.List;

public abstract class Individual extends Customer {
	
	static final long serialVersionUID = 1L;
	
	private String nationality;
	
	private String employerName;
	
	private String occupation;
	
	private String jobTitle;
	
	private int yearsInOccupation;
	
	private byte[] employmentVerificationDocument;
	
	private byte[] payStub;
	
	private String martialStatus;
	
	private char obligationToSupport;
	
	private byte[] divorceDecree;
	
	private char divorceIncomeFlag;
	
	private char isSpouseBorrower;
	
	private String gender;
	
	private String dateOfBirth;
	
	private Address workAddress;
	
	private String ethinicity;	
	
	private List<String> race;
	
	private char isJPMCEmployee;
	
	private char isFirstTimeHomeBuyer;
	
	private char isRegOOfficer;
	
	private String residencyStatus;
	
	private int noOfYearsAtThisResidence;
	
	private char inActiveMilitaryService;
	
	private char generalPartnerinPartnership;
	
	private char isPublicStockOwner;
	
	private char isExecutedWillPresent;
	
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
