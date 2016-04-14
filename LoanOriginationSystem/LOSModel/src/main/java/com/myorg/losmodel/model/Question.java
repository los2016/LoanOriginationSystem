package com.myorg.losmodel.model;

import java.util.List;

public class Question {
	
	private String qnsId;
	private String qnsDecs;
	private List<Answer> answerList;
	
	
	public String getQnsId() {
		return qnsId;
	}
	public void setQnsId(String qnsId) {
		this.qnsId = qnsId;
	}
	public String getQnsDecs() {
		return qnsDecs;
	}
	public void setQnsDecs(String qnsDecs) {
		this.qnsDecs = qnsDecs;
	}
	public List<Answer> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}
	
	
	

}
