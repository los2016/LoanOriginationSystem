package com.myorg.losmodel.model;

import java.io.Serializable;

public final class Person implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	public static final String KEY_CONTACT = "firstName=KEY_CONTACT_FIRST_NM|middleName=MIDDLE_NM|lastName=LAST_NM";
	
	public static final String APRAISAL_CONTACT = "firstName=PROPERTY_ACCESS_CONTACT_FIRST_NM|middleName=PROPERTY_ACCESS_CONTACT_MIDDLE_NM|lastName=PROPERTY_ACCESS_CONTACT_LAST_NM";
	
	public static final String BUSINESS_MANAGER_CONTACT = "";
	
	public static final String INSURANCE_AGENT_CONTACT = "firstName=PROPERTY_ACCESS_CONTACT_FIRST_NM|middleName=PROPERTY_ACCESS_CONTACT_MIDDLE_NM|lastName=PROPERTY_ACCESS_CONTACT_LAST_NM";
	
	public static final String ACCOUNT_CONTACT = "firstName=ACCOUNTING_FIRM_CD|middleName=ACCOUNTANT_MIDDLE_NM|lastName=ACCOUNTANT_LAST_NM";
	
	public static final String CLIENT_NAME = "firstName=CLIENT_LOOKUP_FIRST_NM|middleName=CLIENT_LOOKUP_MIDDLE_NM|lastName=CLIENT_LOOKUP_LAST_NM";
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String title;
	
	private String fullName;  // Should be used in case there is only one field to enter name
	
	private ContactInfo contact;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ContactInfo getContact() {
		return contact;
	}

	public void setContact(ContactInfo contact) {
		this.contact = contact;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}		
	
}
