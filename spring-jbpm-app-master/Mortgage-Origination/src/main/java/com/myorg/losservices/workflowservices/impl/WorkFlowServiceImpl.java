package com.myorg.losservices.workflowservices.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.jbpm.ruleflow.instance.RuleFlowProcessInstance;
import org.jbpm.workflow.instance.node.WorkItemNodeInstance;
import org.junit.Assert;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.NodeInstance;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkItem;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myorg.losservices.workflowservices.WorkFlowService;
import com.myorg.losservices.workitemhandlers.AsyncWorkItemHandler;

import jpmc_pb.MortgageApplication;

public class WorkFlowServiceImpl implements WorkFlowService {

	private static final Logger logger = LoggerFactory.getLogger(WorkFlowServiceImpl.class);

	KieSession ksession = null;

	@Test
	public void executeMortgageOriginationWorkFlow() throws Exception {

		//ksession = createKieSession();
		ksession = loadKieSession();

		MortgageApplication application = new MortgageApplication();
		application.setId(11);

		AsyncWorkItemHandler asyncHandler = new AsyncWorkItemHandler();
		// ContinueWorkItemHandler continueHandler = new
		// ContinueWorkItemHandler();

		ksession.getWorkItemManager().registerWorkItemHandler("Human Task", asyncHandler);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("application", application);
		params.put("reqDescription", "My new requirement");
		ksession.insert(application);
		ProcessInstance instance = ksession.startProcess("Mortgage-Origination.MortgageProcessing", params);
		
		Assert.assertNotNull(instance);
		Assert.assertEquals(ProcessInstance.STATE_ACTIVE, instance.getState());

		WorkItem item1 = asyncHandler.getItem();
		Assert.assertNotNull(item1);
		Map<String, Object> results1 = new HashMap<String, Object>();
		results1.put("result", null);

		ksession.getWorkItemManager().completeWorkItem(item1.getId(), results1);
		Assert.assertEquals(ProcessInstance.STATE_ACTIVE, instance.getState());

		WorkItem item2 = asyncHandler.getItem();
		Assert.assertNotNull(item2);
		Map<String, Object> results2 = new HashMap<String, Object>();
		results2.put("result", application);
		ksession.getWorkItemManager().completeWorkItem(item2.getId(), results2);
		Assert.assertEquals(ProcessInstance.STATE_ACTIVE, instance.getState());

		WorkItem item3 = asyncHandler.getItem();
		Assert.assertNotNull(item3);
		Map<String, Object> results3 = new HashMap<String, Object>();
		results3.put("result", application);
		ksession.getWorkItemManager().completeWorkItem(item3.getId(), results3);
		Assert.assertEquals(ProcessInstance.STATE_ACTIVE, instance.getState());
	}

	/**
	 * Get instance of current work item node
	 * 
	 * @param workItem
	 * @return
	 */
	private WorkItemNodeInstance getCurrentWorkItemNodeInstance(WorkItem workItem) {
		RuleFlowProcessInstance processInst = (RuleFlowProcessInstance) this.ksession
				.getProcessInstance(workItem.getProcessInstanceId());

		Collection<NodeInstance> nodeInstances = processInst.getNodeInstances();

		for (NodeInstance nodeInst : nodeInstances) {
			if (nodeInst instanceof WorkItemNodeInstance) {
				if (((WorkItemNodeInstance) nodeInst).getWorkItem().getId() == workItem.getId()) {
					return ((WorkItemNodeInstance) nodeInst);
				}
			}
		}
		return null;
	}

	/**
	 * Get metadata for current work item node
	 * 
	 * @param workItem
	 * @return
	 */
	private Map<String, Object> getNodeInstanceMetadata(WorkItem workItem) {
		WorkItemNodeInstance workItemNode = getCurrentWorkItemNodeInstance(workItem);

		Map<String, Object> nodeInstanceMetadata = workItemNode.getMetaData();

		return nodeInstanceMetadata;
	}

	/**
	 * Create KIE session and build and ensure there are no errors
	 * 
	 * @return
	 */
	private KieSession createKieSession() {
		KieServices ks = KieServices.Factory.get();
		KieFileSystem kfs = ks.newKieFileSystem();
		kfs.write("src/main/resources/p2.bpmn2",
				ResourceFactory.newClassPathResource("jpmc_pb/mortgage_origination/MortgageProcessing.bpmn2"));
		kfs.write("src/main/resources/sp.drl",
				ResourceFactory.newClassPathResource("jpmc_pb/mortgage_origination/validation.drl"));
		KieBuilder kbuilder = ks.newKieBuilder(kfs);
		kbuilder.buildAll();
		if (kbuilder.getResults().hasMessages(Message.Level.ERROR)) {
			System.out.println(kbuilder.getResults());
			throw new IllegalArgumentException("Invalid knowledge base!! " + kbuilder.getResults());
		}
		KieContainer kcontainer = ks.newKieContainer(kbuilder.getKieModule().getReleaseId());
		final KieSession ksession = kcontainer.newKieSession();
		ksession.addEventListener(new DefaultAgendaEventListener() {
			@Override
			public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
				KieSession kses = (KieSession) event.getKieRuntime();
				kses.fireAllRules();
			}

			@Override
			public void matchCreated(MatchCreatedEvent event) {
				KieSession kses = (KieSession) event.getKieRuntime();
				kses.fireAllRules();
			}
		});
		return ksession;
	}

	private KieSession loadKieSession() {
		KieHelper kieHelper = new KieHelper();
		KieBase kieBase = kieHelper
				.addResource(
						ResourceFactory.newClassPathResource("jpmc_pb/mortgage_origination/MortgageProcessing.bpmn2"),
						ResourceType.BPMN2)
				.addResource(ResourceFactory.newClassPathResource("jpmc_pb/mortgage_origination/validation.drl"),
						ResourceType.DRL)
				.build();
		KieSession ksession = kieBase.newKieSession();
		
		/*
		RuntimeManager manager = createRuntimeManager(kieBase);
        RuntimeEngine engine = manager.getRuntimeEngine(null);
        KieSession ksession = engine.getKieSession();
        TaskService taskService = engine.getTaskService();
		*/
		
		ksession.addEventListener(new DefaultAgendaEventListener() {
			@Override
			public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
				KieSession kses = (KieSession) event.getKieRuntime();
				kses.fireAllRules();
			}

			@Override
			public void matchCreated(MatchCreatedEvent event) {
				KieSession kses = (KieSession) event.getKieRuntime();
				kses.fireAllRules();
			}
		});
		
		return ksession;
	}


}
