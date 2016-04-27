package com.myorg.losmodel.model.questions;

import java.util.Comparator;

public class TimelineSectionWrapperComparator implements Comparator<TimelineSectionWrapper> {

	public int compare(TimelineSectionWrapper o1, TimelineSectionWrapper o2) {
		int ret = 0;
		if(o1.getSequenceNo() > o2.getSequenceNo()){
			ret = 1;
		}else if(o1.getSequenceNo() < o2.getSequenceNo()){
			ret = -1;
		}
		
		return ret;
	}
	
}
