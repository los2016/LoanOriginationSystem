package com.myorg.losmodel.model;

import java.util.List;

public class ValidateQuestionResponse extends LOSResponse {
	
	private List<String> enableFields;
	private List<String> disableFields;
	
	public List<String> getEnableFields() {
		return enableFields;
	}
	public void setEnableFields(List<String> enableFields) {
		this.enableFields = enableFields;
	}
	public List<String> getDisableFields() {
		return disableFields;
	}
	public void setDisableFields(List<String> disableFields) {
		this.disableFields = disableFields;
	}	

}
