package com.myorg.losmodel.model.client;

public class BPMTask{

	public String getTaskName() {
		return taskName;
	}

	public String getTaskstatus() {
		return taskstatus;
	}
	protected String taskName;
	protected String taskstatus;
	
	public BPMTask(String taskName, String taskstatus) {
		this.taskName = taskName;
		this.taskstatus = taskstatus;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}
	
}
