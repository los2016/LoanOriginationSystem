package com.myorg.losmodel.model;

import java.util.List;

public class ValidateQuestionResponse extends LOSResponse {
	
	private List<Integer> enableFields;
	private List<Integer> disableFields;
	
	public List<Integer> getEnableFields() {
		return enableFields;
	}
	public void setEnableFields(List<Integer> enableFields) {
		this.enableFields = enableFields;
	}
	public List<Integer> getDisableFields() {
		return disableFields;
	}
	public void setDisableFields(List<Integer> disableFields) {
		this.disableFields = disableFields;
	}	

}
