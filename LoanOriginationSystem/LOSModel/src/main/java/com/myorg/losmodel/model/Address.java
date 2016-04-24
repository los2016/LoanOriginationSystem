package com.myorg.losmodel.model;

import java.io.Serializable;

public class Address implements Serializable {
	
	static final long serialVersionUID = 1L;
	
	public static final String HOME_ADDRESS = "address1=HOME_ADDRESS_LINE_1_DESC|address2=HOME_ADDRESS_LINE_2_DESC|address3=HOME_ADDRESS_LINE_3_DESC|city=HOME_ADDRESS_CITY_NM|state=HOME_ADDRESS_STATE_CD|zip=HOME_ADDRESS_ZIP_CD";

	public static final String WORK_ADDRESS = "address1=WORK_ADDRESS_LINE_1_DESC|address2=WORK_ADDRESS_LINE_2_DESC|address3=WORK_ADDRESS_LINE_3_DESC|city=WORK_ADDRESS_CITY_NM|state=WORK_ADDRESS_STATE_CD|zip=WORK_ADDRESS_ZIP_CD";
	
	public static final String MAILING_ADDRESS = "address1=MAILING_ADDRESS_LINE_1_DESC|address2=MAILING_ADDRESS_LINE_2_DESC|address3=MAILING_ADDRESS_LINE_3_DESC|city=MAILING_ADDRESS_CITY_NM|state=MAILING_ADDRESS_STATE_CD|zip=MAILING_ADDRESS_ZIP_CD";
	
	public static final String NEW_PROPERTY_DETAILS = "address1=PROPERTY_ADDRESS_LINE_1_DESC|address2=PROPERTY_ADDRESS_LINE_2_DESC|address3=PROPERTY_ADDRESS_LINE_3_DESC|city=PROPERTY_ADDRESS_CITY_NM|state=PROPERTY_ADDRESS_STATE_CD|zip=PROPERTY_ADDRESS_ZIP_CD|";
	
	private int number;
	
	private String address1;
	
	private String address2;
	
	private String address3;
	
	private String city;
	
	private String state;
	
	private String zip; // Kept String because it can include PO Box and special
						// chars
	
	private ContactInfo contactInfo;
	
	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
}
