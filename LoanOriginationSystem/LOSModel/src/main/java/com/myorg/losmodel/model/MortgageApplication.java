package com.myorg.losmodel.model;

import com.myorg.losmodel.model.client.PBEntityClient;
import com.myorg.losmodel.model.client.PBIndividualClient;

public class MortgageApplication {
	
	private long applicationID;
    private String clientPartyId;
    private String advisorPartyId;
    private long bpmProcessId;
    private PBEntityClient entityClient;
    private PBIndividualClient individualClient;
    private MortgageDetails mtgDetails;
    
    
	public long getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(long applicationID) {
		this.applicationID = applicationID;
	}
	public String getClientPartyId() {
		return clientPartyId;
	}
	public void setClientPartyId(String clientPartyId) {
		this.clientPartyId = clientPartyId;
	}
	public String getAdvisorPartyId() {
		return advisorPartyId;
	}
	public void setAdvisorPartyId(String advisorPartyId) {
		this.advisorPartyId = advisorPartyId;
	}
	public long getBpmProcessId() {
		return bpmProcessId;
	}
	public void setBpmProcessId(long bpmProcessId) {
		this.bpmProcessId = bpmProcessId;
	}
	public PBEntityClient getEntityClient() {
		return entityClient;
	}
	public void setEntityClient(PBEntityClient entityClient) {
		this.entityClient = entityClient;
	}
	public PBIndividualClient getIndividualClient() {
		return individualClient;
	}
	public void setIndividualClient(PBIndividualClient individualClient) {
		this.individualClient = individualClient;
	}
	public MortgageDetails getMtgDetails() {
		return mtgDetails;
	}
	public void setMtgDetails(MortgageDetails mtgDetails) {
		this.mtgDetails = mtgDetails;
	}

    
    


}
