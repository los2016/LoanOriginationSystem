package com.myorg.losworkflow.services.impl;

import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;
import org.kie.api.task.model.User;

import com.myorg.losworkflow.helpers.LosRuntimeManager;
import com.myorg.losworkflow.services.WorkFlowService;

/**
 * This is a sample file to launch a process.
 */
public class WorkFlowServiceImpl implements WorkFlowService {

	
	private RuntimeManager manager = LosRuntimeManager.getManager();
	
	private RuntimeEngine engine = LosRuntimeManager.getEngine();

	private KieSession ksession = LosRuntimeManager.getKsession();
    
	private TaskService taskService = LosRuntimeManager.getTaskService();
	
	public long startProcess(String processId, Map<String, Object> params) {
        ProcessInstance pi = ksession.startProcess(processId, params);
        return pi.getId();
    }
	
	public void abortProcess(Long processId) {
        ksession.abortProcessInstance(processId);
    }
	
	public List<Long> getTaskByProcessInstanceId(Long processId) {
        return taskService.getTasksByProcessInstanceId(processId);
    }
	
	public TaskSummary getTaskByProcessUserTaskName(long processInstanceId, String user, String taskName) {
		List<TaskSummary> taskSummaryList = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
		for(TaskSummary taskSummary : taskSummaryList){
			if(processInstanceId == taskSummary.getProcessInstanceId() && taskName.equals(taskSummary.getName())) {
				User taskOwner = taskSummary.getActualOwner();
				String userId = null == taskOwner ? null : taskOwner.getId();
				List<String> potentialTaskOwnerList = taskSummary.getPotentialOwners();
				return taskSummary;
			}
		}
		return null;
    }
	
	public List<TaskSummary> getTaskForUser(String user) {
        return taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
    }
	
	public List<TaskSummary> getTaskForUserByStatus(String user, List<Status> statuslist) {
        return taskService.getTasksAssignedAsPotentialOwnerByStatus(user, statuslist, "en-UK");
    }
	
	public List<TaskSummary> getTaskOfUserByStatus(String user, List<Status> statuslist) {
        return taskService.getTasksOwnedByStatus(user, statuslist, "en-UK");
    }
	
	public List<TaskSummary> getTaskOfUser(String user) {
        return taskService.getTasksOwned(user, "en-UK");
    }
	
	public void claimTask(long taskId, String user) {
        taskService.claim(taskId, user);
    }
	
	public void releaseTask(long taskId, String user) {
        taskService.release(taskId, user);
    }
    
    public void startTask(long taskId, String user) {
        taskService.start(taskId, user);
    }
    
    public void completeTask(long taskId, String user, Map<String, Object> data) {
        taskService.complete(taskId, user, data);
    }
    
    public void suspendTask(long taskId, String user) {
        taskService.suspend(taskId, user);
    }
    
    public void skipTask(long taskId, String user) {
        taskService.skip(taskId, user);
    }

	public RuntimeManager getManager() {
		return manager;
	}

	public RuntimeEngine getEngine() {
		return engine;
	}

	public KieSession getKsession() {
		return ksession;
	}

	public TaskService getTaskService() {
		return taskService;
	}

}