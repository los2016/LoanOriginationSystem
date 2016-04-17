package com.myorg.losmodel.model;

import java.io.Serializable;

public final class Person implements Serializable {
	
	static final long serialVersionUID = 1L;
	
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
