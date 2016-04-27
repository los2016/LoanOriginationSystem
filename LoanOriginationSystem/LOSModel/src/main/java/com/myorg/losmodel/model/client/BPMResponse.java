package com.myorg.losmodel.model.client;

import java.util.ArrayList;
import java.util.List;

public class BPMResponse{

	long bpmProcessId;
	String returnType;
	String returnMessage;
	List<BPMTask> tasks = new ArrayList<BPMTask>();
	
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
