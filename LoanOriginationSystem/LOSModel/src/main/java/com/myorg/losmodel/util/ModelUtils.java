package com.myorg.losmodel.util;

import java.util.ArrayList;
import java.util.List;



public class ModelUtils {
	
	private static List<String> enableqnsIdList = new ArrayList<String>();
	private static List<String> disableqnsIdList = new ArrayList<String>();
	
	private static List<String> qnsIdList = new ArrayList<String>();
	
	public static void addNewQuestionId(String qnsId) {
		
		//if(!StringUtils.isEmpty(qnsId)) {
		if(qnsId != null) {
			
			qnsIdList.add(qnsId);
		}
		
	}
	
	public static void addQnsIdToEnableList(String qnsId) {
		
		//if(!StringUtils.isEmpty(qnsId)) {
		if(enableqnsIdList != null) {
			
			enableqnsIdList.add(qnsId);
		}
		
	}
	
	public static void addQnsIdToDisableList(String qnsId) {
		
		//if(!StringUtils.isEmpty(qnsId)) {
		if(disableqnsIdList != null) {
			
			disableqnsIdList.add(qnsId);
		}
		
	}
	
	public static void cleanQnsIdList() {
		
		//if(!CollectionUtils.isEmpty(qnsIdList)) {
		if(qnsIdList != null) {	
			
			qnsIdList.clear();
			
		}
					
	}
	
	public static void cleanEnableqnsIdList() {
		
		//if(!CollectionUtils.isEmpty(qnsIdList)) {
		if(enableqnsIdList != null) {	
			
			enableqnsIdList.clear();
			
		}
					
	}
	
	public static void cleanDisableqnsIdList() {
		
		//if(!CollectionUtils.isEmpty(qnsIdList)) {
		if(disableqnsIdList != null) {	
			
			disableqnsIdList.clear();
			
		}
					
	}
	
	public static List<String> getQnsListId() {
		return qnsIdList;
	}
	
	public static List<String> getEnableqnsIdList() {
		return enableqnsIdList;
	}
	
	public static List<String> getDisableqnsIdList() {
		return disableqnsIdList;
	}
	

}
