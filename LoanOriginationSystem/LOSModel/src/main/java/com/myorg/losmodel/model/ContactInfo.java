package com.myorg.losmodel.model;

import java.io.Serializable;

public final class ContactInfo implements Serializable{
	
	static final long serialVersionUID = 1L;
	
	public static final String NEW_PROPERTY_CONTACT  = "workphone=BUILDING_CONTACT_PHONE_NO_DESC";
	
	private String homephone;
	
	private String workphone;
	
	private String mobileno;
	
	private String personalemail;	
	
	private String workemail;

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getWorkphone() {
		return workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getPersonalemail() {
		return personalemail;
	}

	public void setPersonalemail(String personalemail) {
		this.personalemail = personalemail;
	}

	public String getWorkemail() {
		return workemail;
	}

	public void setWorkemail(String workemail) {
		this.workemail = workemail;
	}
}
