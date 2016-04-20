package com.myorg.losworkflow.helpers;

import javax.persistence.EntityManagerFactory;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.EnvironmentName;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.task.TaskService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

public class LosRuntimeManager {
	private static RuntimeManager manager;
	
	private static RuntimeEngine engine;

	private static KieSession ksession;
    
	private static TaskService taskService;
	
	private static LosRuntimeManager INSTANCE;
	
	protected LosRuntimeManager(){

	}
	
	public static void setupRuntimeManager(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/kmodule-spring.xml");
		
		KieServices kservices = KieServices.Factory.get();
		KieContainer kContainer = kservices.getKieClasspathContainer();
		KieBase kbase = kContainer.getKieBase("kbase");
	
		EntityManagerFactory emf = (EntityManagerFactory) context.getBean("jbpmEMF");
	
		AbstractPlatformTransactionManager aptm = (AbstractPlatformTransactionManager) context.getBean( "jbpmTxManager" );	

		
		RuntimeEnvironmentBuilder builder = RuntimeEnvironmentBuilder.Factory.get()
        	.newDefaultBuilder()
        	       	//.addAsset(kservices.getResources().newClassPathResource("jpmc_pb/mortgage_origination/MortgageProcessing.bpmn2"), ResourceType.BPMN2)
        	       	//.addAsset(kservices.getResources().newClassPathResource("jpmc_pb/mortgage_origination/validation.drl"), ResourceType.DRL)
		    .addEnvironmentEntry(EnvironmentName.TRANSACTION_MANAGER, aptm)
		    .entityManagerFactory(emf).knowledgeBase(kbase) ;
		
        manager = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(builder.get(), "org.jbpm.MortgageProcessing:1.0");
        
        engine = manager.getRuntimeEngine(null);
        ksession = engine.getKieSession();
        taskService = engine.getTaskService();
	}

	/**
	 * @return the manager
	 */
	public static RuntimeManager getManager() {
		getINSTANCE();
		return LosRuntimeManager.manager;
	}

	/**
	 * @return the engine
	 */
	public static RuntimeEngine getEngine() {
		getINSTANCE();
		return LosRuntimeManager.engine;
	}

	/**
	 * @return the ksession
	 */
	public static KieSession getKsession() {
		getINSTANCE();
		return LosRuntimeManager.ksession;
	}

	/**
	 * @return the taskService
	 */
	public static TaskService getTaskService() {
		getINSTANCE();
		return LosRuntimeManager.taskService;
	}

	/**
	 * @return the iNSTANCE
	 */
	public static LosRuntimeManager getINSTANCE() {
		if(null == INSTANCE) {
			INSTANCE = new LosRuntimeManager();
			setupRuntimeManager();
		}
		return INSTANCE;
	}
}
