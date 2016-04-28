package com.myorg.losmodel.model.client;

import java.util.ArrayList;
import java.util.List;

public class BPMResponse{

	public long getBpmProcessId() {
		return bpmProcessId;
	}

	public String getReturnType() {
		return returnType;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	protected long bpmProcessId;
	protected String returnType;
	protected String returnMessage;
	protected List<BPMTask> tasks = new ArrayList<BPMTask>();
	
	public void setBpmProcessId(long bpmProcessId) {
		this.bpmProcessId = bpmProcessId;
	}
	
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public void setTasks(List<BPMTask> tasks) {
		this.tasks = tasks;
	}

	public List<BPMTask> getTasks() {
		return tasks;
	}
	
}
