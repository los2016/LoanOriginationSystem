package com.jpmorgan.awm.pb.mortgageorigination.businessdelegate;

import org.springframework.beans.factory.annotation.Autowired;

import com.jpmorgan.awm.pb.mortgageorigination.dao.MortgageDAO;
import com.jpmorgan.awm.pb.mortgageorigination.dao.QuestionMetaDataDAO;
import com.myorg.losmodel.model.questions.Timeline;
import com.myorg.losmodel.model.questions.TimelineRequest;





public interface TimeLineBusinessDelegate {

	public Timeline getTimeline(TimelineRequest timelineRequest, MortgageDAO dao) throws Exception;
}
