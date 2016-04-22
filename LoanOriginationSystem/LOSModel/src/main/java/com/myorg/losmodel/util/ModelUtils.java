package com.myorg.losmodel.util;

import java.util.ArrayList;
import java.util.List;



public class ModelUtils {
	
	private static List<Integer> enableqnsIdList = new ArrayList<Integer>();
	private static List<Integer> disableqnsIdList = new ArrayList<Integer>();
	
	private static List<String> qnsIdList = new ArrayList<String>();
	
	public static void addNewQuestionId(String qnsId) {
		
		//if(!StringUtils.isEmpty(qnsId)) {
		if(qnsId != null) {
			
			qnsIdList.add(qnsId);
		}
		
	}
	
	public static void addQnsIdToEnableList(Integer qnsId) {
		
		//if(!StringUtils.isEmpty(qnsId)) {
		if(enableqnsIdList != null) {
			
			enableqnsIdList.add(qnsId);
		}
		
	}
	
	public static void addQnsIdToDisableList(Integer qnsId) {
		
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
	
	public static List<Integer> getEnableqnsIdList() {
		return enableqnsIdList;
	}
	
	public static List<Integer> getDisableqnsIdList() {
		return disableqnsIdList;
	}
	

}
