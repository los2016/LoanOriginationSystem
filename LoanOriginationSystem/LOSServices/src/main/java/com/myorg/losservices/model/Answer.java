package com.myorg.losservices.model;

public class Answer {
	
	private String ansId;
	private String ansDesc;
	private String qnsId;
	private String ansValue;
	private boolean isAnswered = false;
	
	public String getAnsId() {
		return ansId;
	}
	public void setAnsId(String ansId) {
		this.ansId = ansId;
	}
	public String getAnsDesc() {
		return ansDesc;
	}
	public void setAnsDesc(String ansDesc) {
		this.ansDesc = ansDesc;
	}
	public String getQnsId() {
		return qnsId;
	}
	public void setQnsId(String qnsId) {
		this.qnsId = qnsId;
	}
	public String getAnsValue() {
		return ansValue;
	}
	public void setAnsValue(String ansValue) {
		this.ansValue = ansValue;
	}
	public boolean isAnswered() {
		return isAnswered;
	}
	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}	
	

}
