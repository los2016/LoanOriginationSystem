package com.myorg.losmodel.util;

import java.util.ArrayList;
import java.util.List;



public class ModelUtils {
	
private static List<String> qnsIdList = new ArrayList<String>();
	
	public static void addNewQuestionId(String qnsId) {
		
		//if(!StringUtils.isEmpty(qnsId)) {
		if(qnsId != null) {
			
			qnsIdList.add(qnsId);
		}
		
	}
	
	public static void cleanQnsIdList() {
		
		//if(!CollectionUtils.isEmpty(qnsIdList)) {
		if(qnsIdList != null) {	
			
			qnsIdList.clear();
			
		}
					
	}
	
	public static List<String> getQnsListId() {
		return qnsIdList;
	}
	

}
