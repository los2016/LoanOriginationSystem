package com.myorg.losmodel.model.questions;

import java.util.Comparator;

public class SectionComparator implements Comparator<Section> {

	
	public int compare(Section o1, Section o2) {
		int ret = 0;
		if(o1.getSequenceNo() > o2.getSequenceNo()){
			ret = 1;
		}else if(o1.getSequenceNo() < o2.getSequenceNo()){
			ret = -1;
		}
		
		return ret;
	}

	
}
