package com.myorg.losworkflow.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.test.JBPMHelper;
import org.junit.Test;
import org.kie.api.task.model.TaskSummary;

public class WorkFlowServiceImplTest {
	
	@Test
	public void testWorkFlowService(){
		JBPMHelper.startH2Server();
		JBPMHelper.setupDataSource();
		
		WorkFlowServiceImpl workFlowService = new WorkFlowServiceImpl();
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("recipient", "john");
        //params.put("application", application);
        Long instanceId = workFlowService.startProcess("com.myorg.losworkflow.workflow.MortgageOriginationActors", params);
        System.out.println("Process Instance ID " + instanceId + " is generated for application");
        
        // let john execute Task 1
 		List<TaskSummary> list = workFlowService.getTaskForUser("john");
 		TaskSummary task = list.get(0);
 		workFlowService.startTask(task.getId(), "john");
 		workFlowService.completeTask(task.getId(), "john", null);

 		// let mary execute Task 2
 		list = workFlowService.getTaskForUser("mary");
 		task = list.get(0);
 		workFlowService.startTask(task.getId(), "mary");
 		workFlowService.completeTask(task.getId(), "mary", null);
 		
 		// let krisv execute Task 3
 		list = workFlowService.getTaskForUser("krisv");
 		task = list.get(0);
 		workFlowService.startTask(task.getId(), "krisv");
 		workFlowService.completeTask(task.getId(), "krisv", null);
 		
 		// let krisv execute Task 4
 		list = workFlowService.getTaskForUser("krisv");
 		task = list.get(0);
 		workFlowService.startTask(task.getId(), "krisv");
 		workFlowService.completeTask(task.getId(), "krisv", null);
 		
 		// let mary execute Task 5
 		list = workFlowService.getTaskForUser("mary");
 		task = list.get(0);
 		workFlowService.startTask(task.getId(), "mary");
 		workFlowService.completeTask(task.getId(), "mary", null);
 		
 		// let krisv execute Task 6
 		list = workFlowService.getTaskForUser("krisv");
 		task = list.get(0);
 		workFlowService.startTask(task.getId(), "krisv");
 		workFlowService.completeTask(task.getId(), "krisv", null);

 		//workFlowService.getManager.disposeRuntimeEngine(engine);

		//System.exit(0);	
		
	}

}
