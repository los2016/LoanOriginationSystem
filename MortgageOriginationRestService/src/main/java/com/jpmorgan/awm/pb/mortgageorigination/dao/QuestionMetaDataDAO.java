package com.jpmorgan.awm.pb.mortgageorigination.dao;
import com.myorg.losmodel.model.TimelineRequest;
import java.sql.SQLException;
import java.util.Set;


import com.myorg.losmodel.model.client.Timeline;
import com.myorg.losmodel.model.questions.Section;

public interface QuestionMetaDataDAO {

	public Set<Section> questionDAOMethod(String languageCd, String userCd) throws SQLException;
	//public Set<Section> timelineDAOMethod(String languageCd, String userCd) throws SQLException;
	public Timeline getTimeline(TimelineRequest timelineRequest) throws SQLException;

}
