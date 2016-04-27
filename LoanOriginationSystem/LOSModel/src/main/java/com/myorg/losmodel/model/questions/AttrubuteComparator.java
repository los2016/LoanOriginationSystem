package com.myorg.losmodel.model.questions;

import java.util.Comparator;

public class AttrubuteComparator implements Comparator<Attribute> {

	public int compare(Attribute o1, Attribute o2) {
		int ret = 0;
		if(o1.getSequenceNo() > o2.getSequenceNo()){
			ret = 1;
		}else if(o1.getSequenceNo() < o2.getSequenceNo()){
			ret = -1;
		}
		
		return ret;
	}
	
}
