package com.myorg.losmodel.model;

import java.util.Date;
import java.util.List;

public abstract class Individual extends Customer {
	
	private String employerName;
	
	private String martialStatus;
	
	private String gender;
	
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getNameOfEmployer() {
		return nameOfEmployer;
	}

	public void setNameOfEmployer(String nameOfEmployer) {
		this.nameOfEmployer = nameOfEmployer;
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

	public char getIsUSCitizen() {
		return isUSCitizen;
	}

	public void setIsUSCitizen(char isUSCitizen) {
		this.isUSCitizen = isUSCitizen;
	}

	public char getOccupyPropertyAsPrimaryResidence() {
		return occupyPropertyAsPrimaryResidence;
	}

	public void setOccupyPropertyAsPrimaryResidence(char occupyPropertyAsPrimaryResidence) {
		this.occupyPropertyAsPrimaryResidence = occupyPropertyAsPrimaryResidence;
	}

	public char getOwnerShipInterest() {
		return ownerShipInterest;
	}

	public void setOwnerShipInterest(char ownerShipInterest) {
		this.ownerShipInterest = ownerShipInterest;
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

	private String dateOfBirth;
	
	private Date birthDate;
	
	private String nameOfEmployer;
	
	private String ethinicity;	
	
	private List<String> race;
	
	private char isUSCitizen;
	
	private char occupyPropertyAsPrimaryResidence;
	
	private char ownerShipInterest;
	
	private char isJPMCEmployee;
	
	private char isFirstTimeHomeBuyer;
	
	private char isRegOOfficer;
	
	private String residencyStatus;
	
	private double annualIncome;	
}
