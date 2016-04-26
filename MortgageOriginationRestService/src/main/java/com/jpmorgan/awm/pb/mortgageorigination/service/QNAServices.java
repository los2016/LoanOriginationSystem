package com.jpmorgan.awm.pb.mortgageorigination.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.myorg.losmodel.model.GetRuleForQuestionsRequest;
import com.myorg.losmodel.model.GetRuleForQuestionsResponse;
import com.myorg.losmodel.model.ValidateQuestionRequest;
import com.myorg.losmodel.model.ValidateQuestionResponse;

public interface QNAServices {	
	 
	public ValidateQuestionResponse validateQuestion(ValidateQuestionRequest request); 
	public GetRuleForQuestionsResponse getRuleForQuestions(@RequestBody GetRuleForQuestionsRequest request);
	
}
