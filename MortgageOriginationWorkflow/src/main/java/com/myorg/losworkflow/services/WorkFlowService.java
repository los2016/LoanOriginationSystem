package com.myorg.losworkflow.services;

import java.util.List;
import java.util.Map;

import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;

public interface WorkFlowService {
	public long startProcess(String processId, Map<String, Object> params);
	
	public void abortProcess(Long processId);
	
	public List<TaskSummary> getTaskForUser(String user);
	
	public List<TaskSummary> getTaskOfUser(String user);
	
	public List<Long> getTaskByProcessInstanceId(Long processId);
	
	public List<TaskSummary> getTaskForUserByStatus(String user, List<Status> statuslist);
	
	public List<TaskSummary> getTaskOfUserByStatus(String user, List<Status> statuslist);
	
	public void claimTask(long taskId, String user);
	
	public void releaseTask(long taskId, String user);
    
    public void startTask(long taskId, String user);
    
    public void completeTask(long taskId, String user, Map<String, Object> data);
    
    public void suspendTask(long taskId, String user);
    
    public void skipTask(long taskId, String user);
    
}
