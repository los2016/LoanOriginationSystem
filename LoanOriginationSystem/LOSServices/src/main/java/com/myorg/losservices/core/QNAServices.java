package com.myorg.losservices.core;

import java.util.List;

import com.myorg.losservices.model.Answer;
import com.myorg.losservices.model.LoanInfo;
import com.myorg.losservices.model.Question;

public interface QNAServices {

	public List<Question> getInitialQNAList(LoanInfo loadInfo) throws Exception;
	public List<Question> getDependantQNAList(List<Answer> ansList) throws Exception;
}
