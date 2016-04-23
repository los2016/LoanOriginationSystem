package com.myorg.losservices.core.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.drools.core.base.DefaultKnowledgeHelper;
import org.drools.core.beliefsystem.ModedAssertion;
import org.kie.api.cdi.KReleaseId;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.stereotype.Component;

import com.myorg.losmodel.model.Answer;
import com.myorg.losmodel.model.LoanInfo;
import com.myorg.losmodel.model.Question;
import com.myorg.losmodel.model.ValidateQuestionRequest;
import com.myorg.losmodel.model.ValidateQuestionResponse;
import com.myorg.losmodel.util.ModelUtils;
import com.myorg.losservices.core.QNAServices;


@Component
public class QNAServicesImpl implements QNAServices {
	
	Logger logger = Logger.getLogger(QNAServicesImpl.class);
	
	@KSession("defaultKieSession")
	@KReleaseId( groupId = "com.myorg", artifactId = "LOSRules", version = "1.0")
	private KieSession kieSession;
	
	@KSession("defaultStatelessKieSession")
	@KReleaseId( groupId = "com.myorg", artifactId = "LOSRules", version = "1.0")
	private StatelessKieSession kieSessionStateLess;


	@Override
	public List<Question> getInitialQNAList(LoanInfo loadInfo) throws Exception {

		ModelUtils.cleanQnsIdList();
		
		kieSession.insert(loadInfo.getUserInfo());				
		int noOfRulefired = kieSession.fireAllRules();
		
		List<String> qnsIdList = null;
		
		if(noOfRulefired == 0 ) {
			
			//No Rules got fired hence there is no new set of questions
			return new ArrayList<Question>();			
		}
		
		
		qnsIdList = ModelUtils.getQnsListId();
		
		return getQNSDeails(qnsIdList);
		
	}
	
	private List<Question> getQNSDeails(List<String> qnsIdList) {
		
		for(String qnsId: qnsIdList) {
			
			//call db to get details
			
			System.out.println("qnsId: " + qnsId);
		}
		
		//Mocked Data start
		Question qns1 = new Question();
    	qns1.setQnsId("qns-1");
    	qns1.setQnsDecs("It is the 1st QNS");
    	
    	Answer ans11 = new Answer();
    	ans11.setAnsId("ans-11");
    	ans11.setAnsDesc("It is the 1st Ans of 1st Qns");    
    	ans11.setAnsValue("werw");
    	
    	Answer ans12 = new Answer();
    	ans12.setAnsId("ans-12");
    	ans12.setAnsDesc("It is the 2nd Ans of 1st Qns");
    	ans12.setAnswered(true);
    	ans12.setAnsValue("werw");
    	
    	Answer ans13 = new Answer();
    	ans13.setAnsId("ans-13");
    	ans13.setAnsDesc("It is the 3rd Ans of 1st Qns");
    	
    	qns1.setAnswerList(Arrays.asList(ans11, ans12, ans13));
    	
    	
    	Question qns2 = new Question();
    	qns2.setQnsId("qns-2");
    	qns2.setQnsDecs("It is the 2nd QNS");
    	
    	Answer ans21 = new Answer();
    	ans21.setAnsId("ans-11");
    	ans21.setAnsDesc("It is the 1st Ans of 2nd Qns");    
    	ans21.setAnsValue("werw");
    	
    	Answer ans22 = new Answer();
    	ans22.setAnsId("ans-12");
    	ans22.setAnsDesc("It is the 2nd Ans of 2nd Qns");
    	ans22.setAnswered(true);
    	ans22.setAnsValue("werw");
    	
    	Answer ans23 = new Answer();
    	ans23.setAnsId("ans-13");
    	ans23.setAnsDesc("It is the 3rd Ans of 2nd Qns");
    	
    	qns2.setAnswerList(Arrays.asList(ans21, ans22, ans23));
    	
    	
    	Question qns3 = new Question();
    	qns3.setQnsId("qns-3");
    	qns3.setQnsDecs("It is the 3rd QNS");
    	
    	Answer ans31 = new Answer();
    	ans31.setAnsId("ans-31");
    	ans31.setAnsDesc("It is the 1st Ans of 3rd Qns");    
    	ans31.setAnsValue("werw");
    	
    	Answer ans32 = new Answer();
    	ans32.setAnsId("ans-32");
    	ans32.setAnsDesc("It is the 2nd Ans of 3rd Qns");
    	ans32.setAnswered(true);
    	ans32.setAnsValue("werw");
    	
    	Answer ans33 = new Answer();
    	ans33.setAnsId("ans-33");
    	ans33.setAnsDesc("It is the 3rd Ans of 3rd Qns");
    	
    	qns3.setAnswerList(Arrays.asList(ans31, ans32, ans33));
    	
		//Mocked Data End
    	
    	return Arrays.asList(qns1, qns2, qns3);
		
	}

	@Override
	public List<Question> getDependantQNAList(List<Answer> ansList) throws Exception {
		
		if(!CollectionUtils.isEmpty(ansList)) {
			
			for(Answer ans : ansList) {
				
				kieSession.insert(ans);
			}
			
			int noOfRulefired = kieSession.fireAllRules();
			
			List<String> qnsIdList = null;
			
			if(noOfRulefired == 0 ) {
				
				//No Rules got fired hence there is no new set of questions
				return new ArrayList<Question>();			
			}
			
			
			qnsIdList = ModelUtils.getQnsListId();
			
			return getQNSDeails(qnsIdList);
		}
		else {
			
			return new ArrayList<Question>();
			
		}
		
		
		
		

	}

	@Override
	public ValidateQuestionResponse validateQuestion(ValidateQuestionRequest request) {
		
		logger.info("Starting ValidateQuestionResponse ....");
		
		ValidateQuestionResponse resp = null;
		
		ModelUtils.cleanDisableqnsIdList();
		ModelUtils.cleanEnableqnsIdList();
		ModelUtils.cleanValidationMesgList();
		
		
		//kieSessionStateLess.execute(request);
		
		
		
		kieSession.insert(request);
		
		
		/*
		if(request.getPbEntityClient() != null && request.getPbIndividualClient() == null) {
			
			//kieSession.insert(entityClient);			
			kieSessionStateLess.execute(request.getPbEntityClient());
		}
		else if(request.getPbIndividualClient() != null && request.getPbEntityClient() == null) {
			
			//kieSession.insert(indvlClient);			
			kieSessionStateLess.execute(request.getPbIndividualClient());	
			
			/*
				
			client.setEmployerName(indvlClient.getEmployerName());
			client.setMartialStatus(indvlClient.getMartialStatus());
			client.setObligationToSupport(indvlClient.getObligationToSupport());
			client.setDivorceIncomeFlag(indvlClient.getDivorceIncomeFlag());	
			
			if(facthandle == null) {
				
				facthandle  = kieSession.insert(client);
			}
			else {
				kieSession.update(facthandle, client);
			}
			
			*/
			
		/*	
			
		}
		
		else {
			
			resp = new ValidateQuestionResponse();
			resp.setReturnType("error");
			resp.setReturnMsg("Invalid Request, client can be only Individual or Entity");
			
			return resp;
		}		
		
		*/
	
		
		int noOfRulefired = kieSession.fireAllRules();		
		
		if(noOfRulefired == 0 ) {
			

			resp = new ValidateQuestionResponse();
			resp.setReturnType("error");
			resp.setReturnMsg("No Rules got fired for given information");
			
			return resp;
			
		}
		
		if(ModelUtils.getValidationMesgList() != null && ModelUtils.getValidationMesgList().size() > 0) {
			
			resp = new ValidateQuestionResponse();
			resp.setReturnType(ModelUtils.getValidationMesgList().get(0).getType());
			resp.setReturnMsg(ModelUtils.getValidationMesgList().get(0).getMesg());
			
			return resp;
			
		}
		
		
		resp = new ValidateQuestionResponse();
		
		resp.setEnableFields(ModelUtils.getEnableqnsIdList());
		resp.setDisableFields(ModelUtils.getDisableqnsIdList());
		
		resp.setReturnType("success");
		
		return resp;
		
		
	}

}
