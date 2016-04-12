package com.myorg.losservices.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;


public class QNSUtil {
	
	private static List<String> qnsIdList = new ArrayList<String>();
	
	public static void addNewQuestionId(String qnsId) {
		
		if(!StringUtils.isEmpty(qnsId)) {
			
			qnsIdList.add(qnsId);
		}
		
	}
	
	public static void cleanQnsIdList() {
		
		if(!CollectionUtils.isEmpty(qnsIdList)) {
			
			qnsIdList.clear();
			
		}
					
	}
	
	public static List<String> getQnsListId() {
		return qnsIdList;
	}
	
	
	

}
