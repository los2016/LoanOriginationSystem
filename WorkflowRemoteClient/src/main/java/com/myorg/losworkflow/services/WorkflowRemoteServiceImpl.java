package com.myorg.losworkflow.services;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.manager.audit.VariableInstanceLog;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;
import org.kie.remote.client.api.RemoteRuntimeEngineFactory;

public class WorkflowRemoteServiceImpl implements WorkflowRemoteService {
	
	private String deploymentId = null;
	private URL baseUrl = null;
	private String user = null;
	private String password = null;
	private String processName = null;
	//TO-DO - Temporary store for DEMO ONLY to maintain task status based on transition information received from UI
	private static Map<String, String> taskStatusMap = new HashMap<String, String>();
	
	public WorkflowRemoteServiceImpl(String deploymentId, String baseUrl, String user, String password, String processName) throws MalformedURLException {
		this.deploymentId = deploymentId;
		this.baseUrl = new URL( baseUrl );
		this.user = user;
		this.password = password;
		this.processName = processName;
		//taskStatusMap = new HashMap<String, String>();
	}
	
	/**
	 * TO-DO - remove this after demo
	 * @throws MalformedURLException
	 */
	public WorkflowRemoteServiceImpl() throws MalformedURLException {
		deploymentId = "com.jpmorgan.awm.pb.mortgage:MortgageOrigination:1.0";
		baseUrl = new URL( "http://localhost:8080/kie-wb/" );
		user = "krisv";
		password = "krisv";
		processName = "Mortgage-Origination.MortgageProcessing";
	}
	
	
	/**
	 * Start new process instance
	 * @return
	 */
	public Map<String, String> startWorkflow() {
		Map<String, String> resultMap = new HashMap<String, String>();
		
		try {
			RuntimeEngine engine = getRuntimeEngine(user, password);
	
	        // Create KieSession and TaskService instances and use them
	        KieSession ksession = engine.getKieSession();
	
	        // Each opertion on a KieSession, TaskService or AuditLogService (client) instance 
	        // sends a request for the operation to the server side and waits for the response
	        // If something goes wrong on the server side, the client will throw an exception. 
	        ProcessInstance processInstance 
	            = ksession.startProcess(processName);
	        long procId = processInstance.getId();
	        
	        resultMap.put("bpmProcessId", procId + "");
	        resultMap.put("returnType", "success");
	        resultMap.put("returnMessage", "Process created");
	        
	        //Start first task
	        this.transitionToNewTask(procId, "krisv", null, "Apply");
	        this.transitionToNewTask(procId, "krisv", null, "Property Address");
	        
	        //TO-DO - demo
	        taskStatusMap.clear();
	        taskStatusMap.put("Apply", "I");
	        taskStatusMap.put("Property Address", "I");
	        
		} catch (Exception e) {
			resultMap.put("bpmProcessId", null);
	        resultMap.put("returnType", "error");
	        resultMap.put("returnMessage", e.getMessage());
	        e.printStackTrace();
		}
		
		return resultMap;
    }
	
	/**
	 * Suspend current task and transition to next task
	 * @param instanceId
	 * @param userId
	 * @param currentTaskName   //if this is null, transition to new task. Otherwise release and grab new task
	 * @param newTaskName
	 * @return
	 */
	public Map<String, String> transitionToNewTask(long instanceId, String userId, String currentTaskName, String newTaskName) {
		Map<String, String> resultMap = new HashMap<String, String>();
		
		try {
			RuntimeEngine engine = getRuntimeEngine(userId, "krisv");
	
	        // Create KieSession and TaskService instances and use them
	        TaskService taskService = engine.getTaskService();
	        KieSession ksession = engine.getKieSession();
	        
	
	        // Each opertion on a KieSession, TaskService or AuditLogService (client) instance 
	        // sends a request for the operation to the server side and waits for the response
	        // If something goes wrong on the server side, the client will throw an exception. 

	        List<TaskSummary> tasks = taskService.getTasksAssignedAsPotentialOwner(userId, "en-UK");
	        
	        
	        //Stop previous task
	        TaskSummary currentTask = null;
	        if(null != currentTaskName) {
		        long currentTaskId = -1;
		        for (TaskSummary task : tasks) {
		        		//TO-DO - Need to get parent child data from jBPM database
		        		//ProcessInstance instance = ksession.getProcessInstance(task.getProcessInstanceId());
		            //if (task.getProcessInstanceId() == instanceId || task.getProcessInstanceId() == instanceId + 1) {
		                if(currentTaskName.equals(task.getName().trim())) {
		                	currentTaskId = task.getId();
		                	currentTask = task;
		                		break;
		                }
		            //}
		        }
	
		        if (currentTaskId == -1) {
		            System.out.println("Unable to find task " + currentTaskName + " to release for " + userId + " in process instance " + instanceId);
		        } else {
		        		if(null != currentTask && currentTask.getStatus().equals(Status.InProgress)) {
		        			taskService.stop(currentTaskId, userId);
		        		}
		        }
	        }

	        
	        
	        //Start next task
	        long nextTaskId = -1;
	        TaskSummary nextTask = null;
	        for (TaskSummary task : tasks) {
	        		//TO-DO - Need to get parent child data from jBPM database
	        		//ProcessInstance instance = ksession.getProcessInstance(task.getProcessInstanceId());
	            //if (task.getProcessInstanceId() == instanceId || task.getProcessInstanceId() == instanceId + 1) {
	                if(newTaskName.equals(task.getName().trim())) {
	                		nextTask = task;
	                		nextTaskId = task.getId();
	                		break;
	                }
	           // }
	        }

	        if (nextTaskId == -1) {
	            throw new IllegalStateException("Unable to find task for " + userId + " in process instance " + instanceId);
	        }
	        
	        if(!nextTask.getActualOwnerId().equals(userId) && (nextTask.getStatus().equals(Status.Created) || nextTask.getStatus().equals(Status.Ready)) && !nextTask.getStatus().equals(Status.Completed) ) {
	        		try{
	        			taskService.claim(nextTaskId, userId);
	        		} catch(Exception e) {
	        			System.out.println("Unable to claim task " + currentTaskName + " for " + userId + " in process instance " + instanceId);
	        		}
	        }

	        if(nextTask.getStatus().equals(Status.Suspended) || nextTask.getStatus().equals(Status.InProgress)) {
	        		taskService.resume(nextTaskId, userId);
	        } else if ( !nextTask.getStatus().equals(Status.Completed)){
	        	//TO-DO - Check if this check should remain or let exception thrown
	        		taskService.start(nextTaskId, userId);
	        }
	        
	        
	        resultMap.put("bpmProcessId", instanceId + "");
	        resultMap.put("returnType", "success");
	        resultMap.put("returnMessage", "Task " + currentTaskName + " suspended and Task " + newTaskName + " started for process " + instanceId + " started by user " + userId);
	        
	        //TO-DO - Temporary task status storage for DEMO ONLY
	        taskStatusMap.put(currentTaskName, "I");
	        taskStatusMap.put(newTaskName, "I");
		} catch (Exception e) {
			resultMap.put("bpmProcessId", instanceId + "");
	        resultMap.put("returnType", "error");
	        resultMap.put("returnMessage", "Error starting Task " + newTaskName + " for process " + instanceId + " for user " + userId + "  " + e.getMessage());
		}
		
		return resultMap;
    }
	
	
	/**
	 * Mark task as Complete
	 * @param instanceId
	 * @param userId
	 * @param currentTaskName
	 * @return
	 */
	public Map<String, String> markTaskAsComplete(long instanceId, String userId, String currentTaskName) {
		Map<String, String> resultMap = new HashMap<String, String>();
		
		try {
			RuntimeEngine engine = getRuntimeEngine(userId, "krisv");
	
	        // Create KieSession and TaskService instances and use them
	        TaskService taskService = engine.getTaskService();
	        KieSession ksession = engine.getKieSession();
	
	        // Each opertion on a KieSession, TaskService or AuditLogService (client) instance 
	        // sends a request for the operation to the server side and waits for the response
	        // If something goes wrong on the server side, the client will throw an exception. 

	        List<TaskSummary> tasks = taskService.getTasksAssignedAsPotentialOwner(userId, "en-UK");
	        
	        //complete current task
	        long currentTaskId = -1;
	        TaskSummary currentTask = null;
	        for (TaskSummary task : tasks) {
	        		//TO-DO - Need to get parent child data from jBPM database
	        		//ProcessInstance instance = ksession.getProcessInstance(task.getProcessInstanceId());
	            //if (task.getProcessInstanceId() == instanceId || task.getProcessInstanceId() == instanceId + 1) {
	                if(currentTaskName.equals(task.getName().trim())) {
	                		currentTaskId = task.getId();
	                		currentTask = task;
	                		break;
	                }
	            //}
		    }
	
		    if (currentTaskId == -1) {
		        	throw new IllegalStateException("Unable to find task for " + userId + " in process instance " + instanceId);
		    }

		    if(currentTask.getStatus().equals(Status.Suspended) || currentTask.getStatus().equals(Status.Reserved) || currentTask.getStatus().equals(Status.Ready)) {
		    		taskService.start(currentTaskId, userId);
		    		taskService.complete(currentTaskId, userId, null);
		    }
		    else if(!currentTask.getStatus().equals(Status.Completed)) {
		    		taskService.complete(currentTaskId, userId, null);
		    }
		   
	        resultMap.put("bpmProcessId", instanceId + "");
	        resultMap.put("returnType", "success");
	        resultMap.put("returnMessage", "Task " + currentTaskName + " completed for process " + instanceId + " started by user " + userId);
	        
	        //TO-DO - Temporary task status storage for DEMO ONLY
	        taskStatusMap.put(currentTaskName, "C");
		} catch (Exception e) {
			resultMap.put("bpmProcessId", instanceId + "");
	        resultMap.put("returnType", "error");
	        resultMap.put("returnMessage", "Error completing Task " + currentTaskName + " for process " + instanceId + " for user " + userId + "  " + e.getMessage());
		}
		
		return resultMap;
    }
	
	/**
	 * Get status of tasks for the WorkFlow
	 */
	public Map<String, String> getTaksStatusForProcessInstance(long instanceId) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("bpmProcessId", instanceId + "");
		
		//TO-DO - Temporary task status storage for DEMO ONLY
		taskStatusMap.put("bpmProcessId", instanceId + "");
		taskStatusMap.put("returnType", "success");
		return taskStatusMap;
		
		/*
		try {
			RuntimeEngine engine = getRuntimeEngine("kieuser", "kiepassword");
	
			// Create AuditService instances and use them
	        AuditService auditService = engine.getAuditService();
	        
	        List<? extends VariableInstanceLog> variableInstanceLog = auditService.findVariableInstances(instanceId, "status");
	        if(!variableInstanceLog.isEmpty()){
	        		VariableInstanceLog processStatuslog = variableInstanceLog.get(variableInstanceLog.size() - 1);
	        		String processStatusMapAsString = processStatuslog.getValue();
	        		processStatusMapAsString = processStatusMapAsString.replace("{", "").replace("}", "");
	        		
	        		String[] pairs = processStatusMapAsString.split(":");
	        		for (int i=0;i<pairs.length;i++) {
	        		    String pair = pairs[i];
	        		    String[] keyValue = pair.split("=");
	        		    resultMap.put(keyValue[0], keyValue[1]);
	        		}
	        }
	        
	        
	        resultMap.put("returnType", "success");
	        resultMap.put("returnMessage", "");
	        		
		} catch (Exception e) {
			resultMap.put("bpmProcessId", instanceId + "");
	        resultMap.put("returnType", "error");
	        resultMap.put("returnMessage", "");
		}
		return resultMap;
		*/
	}

	/**
	 * @return
	 * @throws MalformedURLException
	 */
	private RuntimeEngine getRuntimeEngine(String user, String password) {
		RuntimeEngine engine = RemoteRuntimeEngineFactory.newRestBuilder()
		        .addDeploymentId(deploymentId)
		        .addUrl(baseUrl)
		        .addUserName(user)
		        .addPassword(password)
		        .build();
		return engine;
	}

}
