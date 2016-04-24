package com.myorg.losmodel.model.questions;

public class QuestionContext {

	public int getQuestionContextId() {
		return questionContextId;
	}

	public void setQuestionContextId(int questionContextId) {
		this.questionContextId = questionContextId;
	}

	public String getQuestionContextNm() {
		return questionContextNm;
	}

	public void setQuestionContextNm(String questionContextNm) {
		this.questionContextNm = questionContextNm;
	}

	protected int questionContextId;
	protected String questionContextNm;

	public boolean equals(QuestionContext qc) {
		boolean ret = false;
		if (qc.getQuestionContextId() == this.getQuestionContextId()) {
			ret = true;
		}
		return ret;
	}

	public int hashCode() {
		return this.getQuestionContextId() % 3;
	}
}
