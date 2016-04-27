package com.jpmorgan.awm.pb.mortgageorigination.businessdelegate.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jpmorgan.awm.pb.mortgageorigination.businessdelegate.TimeLineBusinessDelegate;
import com.jpmorgan.awm.pb.mortgageorigination.dao.QuestionMetaDataDAO;
import com.myorg.losmodel.model.TimelineRequest;
import com.myorg.losmodel.model.client.Timeline;



public class TimeLineBusinessDelegateImpl implements TimeLineBusinessDelegate{
	
	@Autowired
	private QuestionMetaDataDAO questionMetaData;

	
	public Timeline getTimeline(TimelineRequest timelineRequest) throws Exception{
		
		Timeline timeline = null;
		
		try{
			timeline = questionMetaData.getTimeline(timelineRequest);
			
		}catch(Exception e){
			throw new Exception("EXCEPTION IN TIMELINE BUSINESS DELEGATE - ROOT CAUSE ",e);
		}
		return timeline;
	}
	

}
