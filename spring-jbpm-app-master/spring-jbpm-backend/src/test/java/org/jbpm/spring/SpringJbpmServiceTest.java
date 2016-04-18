package org.jbpm.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.jbpm.services.api.UserTaskService;
import org.jbpm.services.api.model.DeployedUnit;
import org.jbpm.services.api.model.DeploymentUnit;
import org.jbpm.services.api.model.ProcessDefinition;
import org.jbpm.services.api.model.ProcessInstanceDesc;
import org.jbpm.services.task.exception.PermissionDeniedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.query.QueryContext;
import org.kie.internal.query.QueryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.kie.spring.manager.SpringRuntimeManagerFactoryImpl;

import jpmc_pb.MortgageApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {"classpath:/applicationContext.xml"})
public class SpringJbpmServiceTest extends AbstractTest {

	@Autowired
	private DeploymentService deploymentService;
	
	@Autowired
	private RuntimeDataService runtimeDataService;
	
	@Autowired
	private ProcessService processService;
	
	@Autowired
	private UserTaskService userTaskService;

	
	@Test
	public void testCompleteOperationSet() {
		assertNotNull(deploymentService);
		
		Collection<DeployedUnit> units = deploymentService.getDeployedUnits();
		assertNotNull(units);
		assertEquals(0, units.size());
		
		
		DeploymentUnit unit = new KModuleDeploymentUnit(GROUP_ID, ARTIFACT_ID, VERSION);
		deploymentService.deploy(unit);
		
		units = deploymentService.getDeployedUnits();
		assertNotNull(units);
		assertEquals(1, units.size());
		
		Collection<ProcessDefinition> processes = runtimeDataService.getProcesses(new QueryContext());
		assertNotNull(processes);
		assertEquals(1, processes.size());
		
		assertNotNull(processService);
		Map<String, Object> params = new HashMap<String, Object>();
		MortgageApplication application = new MortgageApplication();
		application.setId(123);
		application.setFirstName("abc");
		params.put("application", application);


		System.out.println("Starting process");
		//Long processInstanceId = processService.startProcess(unit.getIdentifier(), "Mortgage-Origination.MortgageProcessing");
		
		Long processInstanceId = processService.startProcess(unit.getIdentifier(), "Mortgage-Origination.MortgageProcessing", params);
		assertNotNull(processInstanceId);
		
		
		
		ProcessInstanceDesc piDesc = runtimeDataService.getProcessInstanceById(processInstanceId);
		assertNotNull(piDesc);
		assertEquals(1, piDesc.getState().intValue());
		
		assertNotNull(userTaskService);
		String userId = "kieuser";
		List<TaskSummary> tasks = runtimeDataService.getTasksAssignedAsPotentialOwner(userId, new QueryFilter());
		assertNotNull(tasks);
		assertEquals(1, tasks.size());
		
		long taskId = tasks.get(0).getId();
		
		Task task = userTaskService.getTask(taskId);
		System.out.println(task.getName() + "  " + task.getPeopleAssignments() + "  " + task.getTaskData());
		
		if(!"Reserved".equals(task.getTaskData().getStatus())){
			try {
				userTaskService.claim(taskId, userId);
			} catch (PermissionDeniedException p) {
				System.out.println("Unable to execute operation 'Claim' on task id 1 due to a no 'current status' match");
			}
		}
		
		userTaskService.start(taskId, userId);
		
		userTaskService.complete(taskId, userId, null);
	}
	
   
}
