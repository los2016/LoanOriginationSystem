package com.jpmorgan.awm.pb.mortgageorigination.businessdelegate;

import org.springframework.beans.factory.annotation.Autowired;

import com.jpmorgan.awm.pb.mortgageorigination.dao.QuestionMetaDataDAO;
import com.myorg.losmodel.model.TimelineRequest;
import com.myorg.losmodel.model.client.Timeline;





public interface TimeLineBusinessDelegate {

	public Timeline getTimeline(TimelineRequest timelineRequest) throws Exception;
}
