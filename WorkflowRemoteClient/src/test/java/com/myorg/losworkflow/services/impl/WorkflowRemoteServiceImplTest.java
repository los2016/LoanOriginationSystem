package com.myorg.losworkflow.services.impl;

import java.net.MalformedURLException;
import java.util.Map;

import org.junit.Test;

import com.myorg.losworkflow.services.WorkflowRemoteService;
import com.myorg.losworkflow.services.WorkflowRemoteServiceImpl;

public class WorkflowRemoteServiceImplTest {
	
	@Test
	public void testjavaRemoteApiRest() throws MalformedURLException{
		//Using dummy implementation for demo
		//Should use	 -- public WorkflowRemoteServiceImpl(String deploymentId, String baseUrl, String user, String password, String processName)
		WorkflowRemoteService workflowRemoteService = new WorkflowRemoteServiceImpl();
		
		//Start Process
		Map<String, String> startProcessResultMap = workflowRemoteService.startWorkflow();
		long bpmProcessId = Long.parseLong(startProcessResultMap.get("bpmProcessId"));
		System.out.println(startProcessResultMap);
		
		//Call getTasksForProcessInstance only when required. This is expensive call
		//In test case its added multiple times to demonstrate transition
		Map<String, String> statusResultMap = workflowRemoteService.getTaksStatusForProcessInstance(bpmProcessId);
		System.out.println("Workflow task status:  " + statusResultMap);
		
		//restClient.getTasksForProcessInstance(bpmProcessId);
		
		//First task group will get started as part of workflow startup - so its ok if "Apply" task is not explicitly called.
		//If its easier to keep it generic - no issues if "Apply" is called like below
		Map<String, String> transitionResultMap1 = workflowRemoteService.transitionToNewTask(bpmProcessId, "krisv", null, "Property Address");
		System.out.println(transitionResultMap1);
		//Call getTasksForProcessInstance only when required. This is expensive call
		//In test case its added multiple times to demonstrate transition
		statusResultMap = workflowRemoteService.getTaksStatusForProcessInstance(bpmProcessId);
		System.out.println("Workflow task status:  " + statusResultMap);
		
		Map<String, String> completionResultMap1 = workflowRemoteService.markTaskAsComplete(bpmProcessId, "krisv", "Property Address"); 
		System.out.println(completionResultMap1);
		statusResultMap = workflowRemoteService.getTaksStatusForProcessInstance(bpmProcessId);
		//Call getTasksForProcessInstance only when required. This is expensive call
		//In test case its added multiple times to demonstrate transition
		System.out.println("Workflow task status:  " + statusResultMap);
		
		Map<String, String> transitionResultMap2 = workflowRemoteService.transitionToNewTask(bpmProcessId, "krisv", "Property Address", "Loan Purpose");
		System.out.println(transitionResultMap2);
		//Call getTasksForProcessInstance only when required. This is expensive call
		//In test case its added multiple times to demonstrate transition
		statusResultMap = workflowRemoteService.getTaksStatusForProcessInstance(bpmProcessId);
		System.out.println("Workflow task status:  " + statusResultMap);
		
		Map<String, String> transitionResultMap3 = workflowRemoteService.transitionToNewTask(bpmProcessId, "krisv", "Loan Purpose", "Property Address");
		System.out.println(transitionResultMap3);
		//Call getTasksForProcessInstance only when required. This is expensive call
		//In test case its added multiple times to demonstrate transition
		statusResultMap = workflowRemoteService.getTaksStatusForProcessInstance(bpmProcessId);
		System.out.println("Workflow task status:  " + statusResultMap);
		
		Map<String, String> transitionResultMap4 = workflowRemoteService.transitionToNewTask(bpmProcessId, "krisv", "Property Address", "Basic Info");
		System.out.println(transitionResultMap4);
		//Call getTasksForProcessInstance only when required. This is expensive call
		//In test case its added multiple times to demonstrate transition
		statusResultMap = workflowRemoteService.getTaksStatusForProcessInstance(bpmProcessId);
		System.out.println("Workflow task status:  " + statusResultMap);
		
		Map<String, String> completionResultMap2 = workflowRemoteService.markTaskAsComplete(bpmProcessId, "krisv", "Basic Info"); 
		System.out.println(completionResultMap2);
		
		completionResultMap2 = workflowRemoteService.markTaskAsComplete(bpmProcessId, "krisv", "Loan Purpose"); 
		System.out.println(completionResultMap2);
		
		completionResultMap2 = workflowRemoteService.markTaskAsComplete(bpmProcessId, "krisv", "Loan Terms"); 
		System.out.println(completionResultMap2);
		
		completionResultMap2 = workflowRemoteService.markTaskAsComplete(bpmProcessId, "krisv", "Customer Address"); 
		System.out.println(completionResultMap2);
		
		completionResultMap2 = workflowRemoteService.markTaskAsComplete(bpmProcessId, "krisv", "Mailing Address"); 
		System.out.println(completionResultMap2);
		statusResultMap = workflowRemoteService.getTaksStatusForProcessInstance(bpmProcessId);
		
		completionResultMap2 = workflowRemoteService.markTaskAsComplete(bpmProcessId, "krisv", "Financial Info 1"); 
		System.out.println(completionResultMap2);
		//Call getTasksForProcessInstance only when required. This is expensive call
		//In test case its added multiple times to demonstrate transition
		statusResultMap = workflowRemoteService.getTaksStatusForProcessInstance(bpmProcessId);
		System.out.println("Workflow task status:  " + statusResultMap);
		
		transitionResultMap4 = workflowRemoteService.transitionToNewTask(bpmProcessId, "krisv", null, "ConfirmIntent");
		
		completionResultMap2 = workflowRemoteService.markTaskAsComplete(bpmProcessId, "krisv", "ConfirmIntent"); 
		System.out.println(completionResultMap2);
		
		System.out.println(transitionResultMap4);
		statusResultMap = workflowRemoteService.getTaksStatusForProcessInstance(bpmProcessId);
		System.out.println("Workflow task status:  " + statusResultMap);

	}

}
