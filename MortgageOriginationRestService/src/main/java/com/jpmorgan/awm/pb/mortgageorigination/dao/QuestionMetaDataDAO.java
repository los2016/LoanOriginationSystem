package com.jpmorgan.awm.pb.mortgageorigination.dao;

import java.sql.SQLException;
import java.util.Set;

import com.myorg.losmodel.model.questions.Section;

public interface QuestionMetaDataDAO {

	public Set<Section> questionDAOMethod(String languageCd, String userCd) throws SQLException;

}
