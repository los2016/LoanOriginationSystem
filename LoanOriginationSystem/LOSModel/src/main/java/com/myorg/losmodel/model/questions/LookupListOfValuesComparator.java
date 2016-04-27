package com.myorg.losmodel.model.questions;

import java.util.Comparator;

public class LookupListOfValuesComparator implements Comparator<LookupListOfValues> {
	
	public int compare(LookupListOfValues o1, LookupListOfValues o2) {
		int ret = 0;
		if(o1.getSortOrder() > o2.getSortOrder()){
			ret = 1;
		}else if(o1.getSortOrder() < o2.getSortOrder()){
			ret = -1;
		}
		
		return ret;
	}
	

}
