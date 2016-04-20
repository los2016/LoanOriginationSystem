package com.myorg.losworkflow.services;

import java.util.List;
import java.util.Map;

import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;

public interface WorkFlowService {
	/**
	 * Start process instance by process id and process variables passed as params map
	 * @param processId
	 * @param params
	 * @return
	 */
	public long startProcess(String processId, Map<String, Object> params);
	
	/**
	 * Abort a process
	 * @param processId
	 */
	public void abortProcess(Long processId);
	
	/**
	 * Get list of tasks for which he can take ownership - team's tasks
	 * @param user
	 * @return
	 */
	public List<TaskSummary> getTaskForUser(String user);
	
	/**
	 * Get list of tasks owned by a user
	 * @param user
	 * @return
	 */
	public List<TaskSummary> getTaskOfUser(String user);
	
	/**
	 * Get list of task IDs for a process instance
	 * @param processId
	 * @return
	 */
	public List<Long> getTaskByProcessInstanceId(Long processId);
	
	/**
	 * Get list of tasks for which user can take ownership - team's tasks using status of tasks
	 * @param user
	 * @param statuslist
	 * @return
	 */
	public List<TaskSummary> getTaskForUserByStatus(String user, List<Status> statuslist);
	
	/**
	 * Get list of tasks owned by a user using status of tasks
	 * @param user
	 * @return
	 */
	public List<TaskSummary> getTaskOfUserByStatus(String user, List<Status> statuslist);
	
	/**
	 * Claim a task for which user is not owner but is in his team's tasks
	 * @param taskId
	 * @param user
	 */
	public void claimTask(long taskId, String user);
	
	/**
	 * Release a task owned by user and return it to team's tasks
	 * @param taskId
	 * @param user
	 */
	public void releaseTask(long taskId, String user);
    
	/**
	 * Start a task
	 * @param taskId
	 * @param user
	 */
    public void startTask(long taskId, String user);
    
    /**
     * Complete a task
     * @param taskId
     * @param user
     * @param data
     */
    public void completeTask(long taskId, String user, Map<String, Object> data);
    
    /**
     * Suspend a task
     * @param taskId
     * @param user
     */
    public void suspendTask(long taskId, String user);
    
    /**
     * Skip a task provided it is not a mandatory task
     * @param taskId
     * @param user
     */
    public void skipTask(long taskId, String user);
    
}
