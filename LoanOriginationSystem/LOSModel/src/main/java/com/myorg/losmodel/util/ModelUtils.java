package com.myorg.losmodel.util;

import java.util.ArrayList;
import java.util.List;

import com.myorg.losmodel.model.ValidateMesg;



public class ModelUtils {
	
	private static List<Integer> enableqnsIdList = new ArrayList<Integer>();
	private static List<Integer> disableqnsIdList = new ArrayList<Integer>();
	
	private static List<ValidateMesg> validationMesgList = new ArrayList<ValidateMesg>();
	
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
	
	public static void addMesgToValidationMesgList(ValidateMesg mesg) {
		
		if(validationMesgList != null) {
			
			validationMesgList.add(mesg);
		}
	}
	
	public static void addMesgToValidationMesgList(String type, String mesg) {
		
		ValidateMesg vMesg = new ValidateMesg();
		vMesg.setType(type);
		vMesg.setMesg(mesg);
		
		addMesgToValidationMesgList(vMesg);
		
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
	
	public static void cleanValidationMesgList() {
		
		if(validationMesgList != null) {
			
			validationMesgList.clear();
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
	
	public static List<ValidateMesg> getValidationMesgList() {
		return validationMesgList;
	}
	

}
