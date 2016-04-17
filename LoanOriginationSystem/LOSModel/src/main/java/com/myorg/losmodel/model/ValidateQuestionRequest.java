package com.myorg.losmodel.model;

import com.myorg.losmodel.model.client.PBEntityClient;
import com.myorg.losmodel.model.client.PBIndividualClient;

public class ValidateQuestionRequest extends LOSRequest {	
	
	private String questionId;
	private PBEntityClient pbEntityClient;
	private PBIndividualClient pbIndividualClient;
	
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public PBEntityClient getPbEntityClient() {
		return pbEntityClient;
	}
	public void setPbEntityClient(PBEntityClient pbEntityClient) {
		this.pbEntityClient = pbEntityClient;
	}
	public PBIndividualClient getPbIndividualClient() {
		return pbIndividualClient;
	}
	public void setPbIndividualClient(PBIndividualClient pbIndividualClient) {
		this.pbIndividualClient = pbIndividualClient;
	}	

}
