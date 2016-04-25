package com.myorg.losworkflow.services;

import java.util.Map;

public interface WorkflowRemoteService {
	
	
	/**
	 * Start new process instance
	 * @return
	 */
	public Map<String, String> startWorkflow();
	
	/**
	 * Suspend current task and transition to next task
	 * @param instanceId
	 * @param userId
	 * @param currentTaskName   //if this is null, transition to new task. Otherwise release and grab new task
	 * @param newTaskName
	 * @return
	 */
	public Map<String, String> transitionToNewTask(long instanceId, String userId, String currentTaskName, String newTaskName);
	
	
	/**
	 * Mark task as Complete
	 * @param instanceId
	 * @param userId
	 * @param currentTaskName
	 * @return
	 */
	public Map<String, String> markTaskAsComplete(long instanceId, String userId, String currentTaskName);
	
	public Map<String, String> getTaksStatusForProcessInstance(long instanceId);


}
