package com.myorg.losservices.core;

import java.util.List;

import com.myorg.losmodel.model.Answer;
import com.myorg.losmodel.model.LoanInfo;
import com.myorg.losmodel.model.Question;
import com.myorg.losmodel.model.ValidateQuestionRequest;
import com.myorg.losmodel.model.ValidateQuestionResponse;

public interface QNAServices {

	public List<Question> getInitialQNAList(LoanInfo loadInfo) throws Exception;
	public List<Question> getDependantQNAList(List<Answer> ansList) throws Exception;
	public ValidateQuestionResponse validateQuestion(ValidateQuestionRequest request); 
	
}
