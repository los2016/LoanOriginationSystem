package com.myorg.losmodel.model.questions;

import java.util.Comparator;

public class QuestionComparator implements Comparator<Question> {


	public int compare(Question o1, Question o2) {
		int ret = 0;
		if(o1.getSequenceNo() > o2.getSequenceNo()){
			ret = 1;
		}else if(o1.getSequenceNo() < o2.getSequenceNo()){
			ret = -1;
		}
		
		return ret;
	}

}
