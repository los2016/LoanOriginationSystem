package com.jpmorgan.awm.pb.mortgageorigination.dao;
import java.sql.SQLException;
import java.util.Set;

import com.myorg.losmodel.model.User;
import com.myorg.losmodel.model.questions.Section;

public interface QuestionMetaDataDAO {

	public Set<Section> questionDAOMethod(String languageCd, String userCd) throws SQLException;
	//public Set<Section> timelineDAOMethod(String languageCd, String userCd) throws SQLException;
	public Set<Section> getTimelineSections(com.myorg.losmodel.model.questions.TimelineRequest timelineRequest) throws SQLException;
	public User getUserDetails(String userCd) throws Exception;
}
