package com.myorg.losmodel.model;

import java.util.List;

public class GetRuleForQuestionsResponse extends LOSResponse {
	
	private List<Question> questionRules;

	public List<Question> getQuestionRules() {
		return questionRules;
	}

	public void setQuestionRules(List<Question> questionRules) {
		this.questionRules = questionRules;
	}
	
	

}
