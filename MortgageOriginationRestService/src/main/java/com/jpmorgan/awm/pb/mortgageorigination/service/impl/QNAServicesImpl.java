package com.jpmorgan.awm.pb.mortgageorigination.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kie.api.cdi.KReleaseId;
import org.kie.api.cdi.KSession;
import org.kie.api.definition.KiePackage;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.jpmorgan.awm.pb.mortgageorigination.service.QNAServices;
import com.myorg.losmodel.model.GetRuleForQuestionsRequest;
import com.myorg.losmodel.model.GetRuleForQuestionsResponse;
import com.myorg.losmodel.model.Question;
import com.myorg.losmodel.model.ValidateQuestionRequest;
import com.myorg.losmodel.model.ValidateQuestionResponse;
import com.myorg.losmodel.util.ModelUtils;


@Service
public class QNAServicesImpl implements QNAServices {
	
	//Logger logger = Logger.getLogger(QNAServicesImpl.class);
	
	
	@KSession("defaultKieSession")
	@KReleaseId( groupId = "com.myorg", artifactId = "LOSRules", version = "1.0")
	private KieSession kieSession;
	
	@KSession("defaultStatelessKieSession")
	@KReleaseId( groupId = "com.myorg", artifactId = "LOSRules", version = "1.0")
	private StatelessKieSession kieSessionStateLess;
	
	

	
	public ValidateQuestionResponse validateQuestion(ValidateQuestionRequest request) {
	
	
	//logger.info("Starting ValidateQuestionResponse ....");
		
		ValidateQuestionResponse resp = null;
		
		ModelUtils.cleanDisableqnsIdList();
		ModelUtils.cleanEnableqnsIdList();
		ModelUtils.cleanValidationMesgList();		
		
		kieSession.insert(request);	
	
		int noOfRulefired = kieSession.fireAllRules();		
		
		if(noOfRulefired == 0 ) {
			

			resp = new ValidateQuestionResponse();
			resp.setReturnType("error");
			resp.setReturnMsg("No Rules got fired for given information");
			
			return resp;
			
		}
		
		
		/*
		if(ModelUtils.getValidationMesgList() != null && ModelUtils.getValidationMesgList().size() > 0) {
			
			resp = new ValidateQuestionResponse();
			resp.setReturnType(ModelUtils.getValidationMesgList().get(0).getType());
			resp.setReturnMsg(ModelUtils.getValidationMesgList().get(0).getMesg());
			
			return resp;
			
		}
		*/
		
		
		resp = new ValidateQuestionResponse();
		
		resp.setEnableFields(ModelUtils.getEnableqnsIdList());
		resp.setDisableFields(ModelUtils.getDisableqnsIdList());
		resp.setValidateMesgList(ModelUtils.getValidationMesgList());
		
		resp.setReturnType("success");
		
		return resp;		
	}
	
	@Override
	public GetRuleForQuestionsResponse getRuleForQuestions(@RequestBody GetRuleForQuestionsRequest request) {
		
		GetRuleForQuestionsResponse resp = null;
		
		List<Question> qnsList = new ArrayList<Question>();
		
		List<KiePackage> packageList = (List<KiePackage>) kieSession.getKieBase().getKiePackages();
		
		Question qns = null;
		for(KiePackage kiePackage : packageList) {
			
			System.out.println("Kie Packages: " + kiePackage.getName());
			
			String qnsId = StringUtils.substringAfter(kiePackage.getName(),"q");
			
			if(!qnsId.isEmpty()) {
				
				qns = new Question();
				qns.setQuestionId(new Integer(qnsId));
				qns.setRule("Y");
				
				qnsList.add(qns);
				
			}
		}
		 
		
		resp = new GetRuleForQuestionsResponse();
		resp.setQuestionRules(qnsList);
		resp.setReturnType("success");
		
		return resp;
		
	}	

}
