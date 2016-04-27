package com.jpmorgan.awm.pb.mortgageorigination.dao.impl;


//Shubhrajit - 26/4 - Refactoring to break up into different methods


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmorgan.awm.pb.mortgageorigination.dao.QuestionMetaDataDAO;
import com.jpmorgan.awm.pb.mortgageorigination.utils.DatabaseService;
import com.myorg.losmodel.model.TimelineRequest;
import com.myorg.losmodel.model.client.MortgageApplication;
import com.myorg.losmodel.model.client.Timeline;
import com.myorg.losmodel.model.questions.Attribute;
import com.myorg.losmodel.model.questions.DataType;
import com.myorg.losmodel.model.questions.LookupListOfValues;
import com.myorg.losmodel.model.questions.Question;
import com.myorg.losmodel.model.questions.QuestionContext;
import com.myorg.losmodel.model.questions.Role;
import com.myorg.losmodel.model.questions.Section;
import com.myorg.losmodel.util.ModelUtils;

@Service
public class QuestionMetaDataDAOImpl implements QuestionMetaDataDAO {

	@Autowired
	private DataSource dataSource;

	Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	public String getLanguageCdDAO(Connection conn, String languageCd,String userCd) throws SQLException{
			
		try {
			
			
			PreparedStatement languageCdPS = conn
					.prepareStatement("select default_language_cd from mortgage.users where user_cd = ?");

			if ("default".equals(languageCd) || languageCd == null) {
				languageCdPS.setString(1, userCd);
				ResultSet defaultLangRs = languageCdPS.executeQuery();
				while (defaultLangRs.next()) {
					languageCd = defaultLangRs.getString("default_language_cd");
				}

			}
			
			if (languageCd == null) {
				languageCd = "en";

			}

			System.out.println("LANGUAGE CODE IS SET TO:-"+languageCd);
		}catch (SQLException e){
		    languageCd = "en";
			throw e;
		}
		return languageCd;	
	}
	public Set<Section> getSectionDAO(Connection conn,String languageCd) throws SQLException{
		//Connection conn = null;
		// Question question = null;
		TreeSet<Section> sectionSet = new TreeSet<Section>();
		PreparedStatement sectionPS = null;
			if("en".equals(languageCd)){
				sectionPS = conn.prepareStatement(
					"select s.section_id,s.present_section_nm, s.past_section_nm, s.future_section_nm, s.sequence_no,"
							+ " s.parent_section_id as parent_id,"
							+ " null as i18n_present_nm, null as i18N_past_nm,null as i18n_future_nm "
							+ " from mortgage.section_metadata s"							
				);
			}else{
				sectionPS = conn.prepareStatement(
						"select s.section_id,s.present_section_nm, s.past_section_nm, s.future_section_nm, s.sequence_no,"
								+ " s.parent_section_id as parent_id,"
								+ " sin.present_section_nm as i18n_present_nm, sin.past_section_nm as i18N_past_nm,sin.future_section_nm as i18n_future_nm "
								+ " from mortgage.section_metadata s"
								+ " left outer join mortgage.section_metadata_i18n sin on sin.section_id = s.section_id"
								+ " where sin.language_iso2_cd = ?"
				);
				sectionPS.setString(1,languageCd);
			
			}
			ResultSet sectionRS = sectionPS.executeQuery();

			while (sectionRS.next()) {

				// First we will create a new section with the section id from
				// the resultset
				Section sectionFromResultSet = new Section();
				int sectionId = sectionRS.getInt("section_id");
				sectionFromResultSet.setSectionId(sectionId);
				// System.out.println("Section ID"+sectionId);

				// As we are building the sections, we will use the equals
				// method to see the section is already in the collection
				// created in a previous
				// iteration
				Section sectionFromCollection = findSectionInSet(sectionFromResultSet, sectionSet);
				if (sectionFromCollection == null) {
					sectionFromCollection = sectionFromResultSet;
					// If the section is not already in my set, I add it
					sectionSet.add(sectionFromCollection);
				}

				// this will be repeated - a wasted call. Opportunity to
				// optimize in the future

				String enPresentNm = sectionRS.getString("present_section_nm");
				String enPastNm = sectionRS.getString("past_section_nm");
				String enFutureNm = sectionRS.getString("future_section_nm");
				
				String i18NPresentNm = sectionRS.getNString("i18n_present_nm");
				String i18NPastNm = sectionRS.getNString("i18n_past_nm");
				String i18NFutureNm = sectionRS.getNString("i18n_future_nm");

				// Since we have an outer join it can come as null. We do not
				// want to add nulls in the object
				
					if (i18NPresentNm != null) {
						enPresentNm = i18NPresentNm;
					}
					if (i18NPastNm != null) {
						
						enPastNm = i18NPastNm;
					}
					if (i18NFutureNm != null) {
						enFutureNm = i18NFutureNm;
					}
					sectionFromCollection.setPresentSectionNm(enPresentNm);
					sectionFromCollection.setPastSectionNm(enPastNm);
					sectionFromCollection.setFutureSectionNm(enFutureNm);	
			

				int sequenceNo = sectionRS.getInt("sequence_no");
			
				sectionFromCollection.setSequenceNo(sequenceNo);

				int parentSectionId = sectionRS.getInt("parent_id");
				// System.out.println("Parent Section ID"+parentSectionId);

				// It is important we know the children of a section. However
				// initially we would try to know the parent. Later on we will
				// do it upside down to get
				// the levels and the children
				
				//Section parentFromResultSetStub = null;

				//if (parentSectionId > 0) {
					//parentFromResultSetStub = new Section();
					//parentFromResultSetStub.setSectionId(parentSectionId);
				//}
				// If the parent is not already in the collection. create it.
				// Take advantage of the equals method

				//Section parentFromSet = null;
				//if (parentFromResultSet != null) {
					//parentFromSet = this.findSectionInSet(parentFromResultSet, sectionSet);
				//}
				//if (parentFromSet == null) {
					//parentFromSet = parentFromResultSet;
					//if (parentFromSet != null) {
						//sectionSet.add(parentFromSet);
					//}
				//}
			
				sectionFromCollection.setParentSectionId(parentSectionId);

				}

		
			// When we are here all the sections know who is their parent, but
			// we also need to know the level
			// and list of children
			// So we pass the collection to a helper method to get all the
			// children

			computeChildrenAndLevelsForSection(sectionSet);
			// Now we have the sections

			Iterator<Section> i = sectionSet.iterator();
			
			while(i.hasNext()){ 
				Section check = i.next();
				//System.out.println("SECTION:"+check.getSectionId()+ "PARENT SECTION ID : "+check.getParentSectionId()+" NO OF CHILDREN: "+check.getChildSections().size());

			}
			
			return sectionSet;
			
	}
	
	
	public Set<Question> getQuestionDAO(Connection conn,String languageCd,Set<Section> sectionSet) throws SQLException{
		PreparedStatement questionPS = null;
		TreeSet<Question> questions = new TreeSet<Question>();
		
		if ("en".equals(languageCd)) {
			questionPS = conn.prepareStatement(
					"select q.question_id,q.question_long_desc,q.section_id,q.parent_question_id,q.role_id,q.question_context_help_desc,"
							+ " q.mandatory_cd,q.sequence_no,r.ROLE_NM,q.QUESTION_CONTEXT_ID,qc.QUESTION_CONTEXT_DESC,null as q_long_desc_i18n, null as q_context_help_desc_i18n"
							+ " from mortgage.question_metadata q"
							+ " inner join role_metadata r on r.role_id = q.role_id"
							+ " inner join question_context_metadata qc on q.QUESTION_CONTEXT_ID = qc.question_context_id"

							+ " where q.logical_del_fl = 'N'");
		} else {

			questionPS = conn.prepareStatement(
					"select q.question_id,q.question_long_desc,q.section_id,q.parent_question_id,q.role_id,q.question_context_help_desc,"
							+ " q.mandatory_cd,q.sequence_no,r.ROLE_NM,q.QUESTION_CONTEXT_ID,qc.QUESTION_CONTEXT_DESC,qi.question_long_desc as q_long_desc_i18n, qi.question_context_help_desc as q_context_help_desc_i18n"
							+ " from mortgage.question_metadata q"
							+ " inner join role_metadata r on r.role_id = q.role_id"
							+ " inner join question_context_metadata qc on q.QUESTION_CONTEXT_ID = qc.question_context_id"
							+ " left outer join question_attribute_i18n qi on q.question_id = qi.question_id"
							+ " where q.logical_del_fl = 'N' and qi.language_iso2_cd = ? ");
		}

		
		// System.out.println(languageCd);
		if (!"en".equals(languageCd)) {
			questionPS.setString(1, languageCd);
		}
		ResultSet questionRs = questionPS.executeQuery();

		while (questionRs.next()) {
			// System.out.println("1");
			Question q = new Question();
			q.setQuestionId(questionRs.getInt("question_id"));
			String questionDescI18N = questionRs.getNString("q_long_desc_i18n");
			String questionDesc = questionRs.getString("question_long_desc");
			if (!((questionDescI18N == null) || ("".equals(questionDescI18N)))) {
				questionDesc = questionDescI18N;
			}
			q.setQuestionLongDesc(questionDesc);

			String toolTipI18N = questionRs.getNString("q_context_help_desc_i18n");
			String toolTip = questionRs.getString("question_context_help_desc");
			if (!((toolTipI18N == null) || ("".equals(toolTipI18N)))) {
				toolTip = toolTipI18N;
			}

			q.setToolTip(toolTip);

			// System.out.println(q.getQuestionId()+q.getQuestionLongDesc());

			Section questionSectionFromRS = new Section();
			questionSectionFromRS.setSectionId(questionRs.getInt("section_id"));

			Section questionSectionFromSet = findSectionInSet(questionSectionFromRS, sectionSet);
			//questionSectionFromSet.setActiveLanguage(languageCd);
			//q.setSection(questionSectionFromSet);
			questionSectionFromSet.addQuestion(q);
			int parentQuestionId = questionRs.getInt("parent_question_id");
			
			q.setParentQuestionId(parentQuestionId);
			//Question parentQuestionFromResultSet = null;
			//
			//if (parentQuestionId > 0) {
				//parentQuestionFromResultSet = new Question();
				//parentQuestionFromResultSet.setQuestionId(parentQuestionId);
			//}
			// If the parent is not already in the collection. create it.
			// Take advantage of the equals method

			//Question parentQuestionFromSet = null;
			//if (parentQuestionFromResultSet != null) {
				//parentQuestionFromSet = findQuestionInSet(parentQuestionFromResultSet, questions);
			//}
			//if (parentQuestionFromSet == null) {
				//parentQuestionFromSet = parentQuestionFromResultSet;
				//if (parentQuestionFromSet != null) {
					//questions.add(parentQuestionFromSet);
				//}
			//}

			Role r = new Role();
			r.setRoleId(questionRs.getInt("role_id"));
			r.setRoleNm(questionRs.getString("role_nm"));

			q.setRole(r);

			QuestionContext qc = new QuestionContext();
			qc.setQuestionContextId(questionRs.getInt("question_context_id"));
			qc.setQuestionContextNm(questionRs.getString("QUESTION_CONTEXT_DESC"));
			q.setQuestionContext(qc);

			q.setMandatoryCd(questionRs.getString("mandatory_cd"));
			q.setSequenceNo(questionRs.getInt("sequence_no"));
			questions.add(q);
			// When we are here all the sections know who is their parent,
			// but we also need to know the
			// list of children
			// So we pass the collection to a helper method to get all the
			// children
		}
			computeChildrenForQuestion(questions);

			// All questions read

			Iterator<Question> qi =  questions.iterator();
			while(qi.hasNext()){
				Question qqi = qi.next();
				//System.out.println("QUESTION ID:"+qqi.getQuestionId()+"Question DESC:"+qqi.getQuestionLongDesc()+"No of attributes:"+qqi.getAttributes().size());
			}
			
			return questions;
	}
	
	
	public Set<Attribute> getAttributeDAO(Connection conn,String languageCd,Set<Question> questions) throws SQLException{
	
		// Now let get the attributes and add them to the questions
		PreparedStatement attrPs = null;
		if (!"en".equals(languageCd)) {
			attrPs = conn.prepareStatement(

					" select m.question_id,a.attribute_id,a.col_nm,a.col_desc,a.datatype_id,d.DATATYPE_NM,"
							+ "a.lookup_entity_nm,i.LANGUAGE_ISO2_CD, i.COL_DESC as col_desc_i18n, m.sequence_no "
							+ "from mortgage.attribute_metadata a "
							+ "left outer join mortgage.attribute_i18n i on a.attribute_id = i.attribute_id "
							+ "inner join mortgage.question_attribute_map m on m.col_id = a.attribute_id "
							+ "inner join mortgage.datatype_metadata d on d.datatype_id = a.datatype_id "
							+ "where a.logical_del_fl = 'N' and i.language_iso2_cd = ?");

			attrPs.setString(1, languageCd);
		} else {

			attrPs = conn.prepareStatement(

					" select m.question_id,a.attribute_id,a.col_nm,a.col_desc,a.datatype_id,d.DATATYPE_NM,"
							+ "a.lookup_entity_nm, null as LANGUAGE_ISO2_CD, null as col_desc_i18n,m.sequence_no "
							+ "from mortgage.attribute_metadata a "

							+ "inner join mortgage.question_attribute_map m on m.col_id = a.attribute_id "
							+ "inner join mortgage.datatype_metadata d on d.datatype_id = a.datatype_id "
							+ "where a.logical_del_fl = 'N' ");
		}
		ResultSet attrRs = attrPs.executeQuery();

		HashSet<Attribute> attributes = new HashSet<Attribute>();
		while (attrRs.next()) {
			Attribute attrFromResultSet = new Attribute();
			attrFromResultSet.setAttributeId(attrRs.getInt("attribute_id"));
			Question questionFromResultSet = new Question();
			questionFromResultSet.setQuestionId(attrRs.getInt("question_id"));

			Question questionFromSet = null;
			if (questionFromResultSet != null) {
				questionFromSet = findQuestionInSet(questionFromResultSet, questions);
			}
			if (questionFromSet == null) {
				questionFromSet = questionFromResultSet;
			}

			questionFromSet.addAttribute(attrFromResultSet);
			attrFromResultSet.setColName(attrRs.getString("col_nm"));

			// Added by Gagan as per Vaibhav Model
			//String attrFQNColName = ModelUtils.getDbAttributeToObjectNamesMap().get(attrRs.getString("col_nm"));
			String attrFQNColName = "";
			Map<String,String> attToObjectMap =  ModelUtils.getDbAttributeToObjectNamesMap();
			if(attToObjectMap == null){
				System.err.println("Attribute to Fully Qualified Map is NULL - Something is wrong");
			}else{
				
				
				attrFQNColName = attToObjectMap.get(attrFromResultSet.getColName());
				attrFromResultSet.setObjectAttrFQN(attrFQNColName);
				System.err.println("DB COL NAME TO LOOKUP ATTRIBUTE: "+attrFromResultSet.getColName()+" FQN = "+attrFQNColName);
				
				
				
			}
			
			

			String attrDescI18N = attrRs.getNString("col_desc_i18n");
			String attrDesc = attrRs.getString("col_desc");
			if (!((attrDescI18N == null) || ("".equals(attrDescI18N)))) {
				attrDesc = attrDescI18N;
			}
			attrFromResultSet.setColDesc(attrDesc);
			int datatypeId = attrRs.getInt("datatype_id");
			String datatypeNm = attrRs.getString("DATATYPE_NM");
			DataType dt = new DataType();
			dt.setDataTypeId(datatypeId);
			dt.setDataTypeNm(datatypeNm);
			attrFromResultSet.setDataType(dt);
			attrFromResultSet.setSequenceNo(attrRs.getInt("sequence_no"));
			attrFromResultSet.setLookupEntityNm(attrRs.getString("lookup_entity_nm"));
			attributes.add(attrFromResultSet);
			

		}

		// Here I have the list of attributes - only thing missing is
		// the List of values
		return attributes;
		
	}
	
	
	public void getLOVDAO(Connection conn,String languageCd, Set<Attribute> attributes) throws SQLException{
		int index = 1;
		PreparedStatement ps4 = null;
		if ("en".equals(languageCd)) {
			ps4 = conn.prepareStatement(
					"select r.lookup_entity_nm, r.lookup_cd,r.lookup_desc, r.sort_order_no,null as lookup_desc_i18n from mortgage.reference_lookup r"

							+ " where r.logical_del_fl = 'N' and r.lookup_entity_nm = ?");

		} else {

			ps4 = conn.prepareStatement(
					"select r.lookup_entity_nm, r.lookup_cd,r.lookup_desc, r.sort_order_no,i.lookup_desc as lookup_desc_i18n from mortgage.reference_lookup r"
							+ " left outer join mortgage.reference_lookup_i18n i on i.lookup_entity_nm = r.lookup_entity_nm and r.lookup_cd = i.lookup_cd"
							+ " where r.logical_del_fl = 'N' and i.language_iso2_cd = ? and r.lookup_entity_nm = ?");
			index = 2;
			ps4.setString(1, languageCd);

		}

		Iterator<Attribute> it = attributes.iterator();
		while (it.hasNext()) {
			Attribute a = it.next();
			//System.out.println(a.getLookupEntityNm());
			ps4.setString(index, a.getLookupEntityNm());
			ResultSet lookupRs = ps4.executeQuery();
			while (lookupRs.next()) {

				LookupListOfValues lov = new LookupListOfValues();
				lov.setLookupCd(lookupRs.getString("lookup_cd"));
				lov.setSortOrder(lookupRs.getInt("sort_order_no"));
				String lookupDescI18N = lookupRs.getNString("lookup_desc_i18n");
				String lookupDesc = lookupRs.getString("lookup_desc");
				if (!((lookupDescI18N == null) || ("".equals(lookupDescI18N)))) {
					lookupDesc = lookupDescI18N;
				}
				lov.setLookupValue(lookupDesc);
				//System.out.println(lov.toString());
				a.addListOfValues(lov);
			}

		}

		// Now - Section contains questions - question contains both
		// section and attributes, attributes contain LOV

	}
	

	
	
	
	/**
	 * Method to get the sections / questions / attributes / LOVS for a user
	 * based on specified language or default language if language is not
	 * selected
	 * 
	 * NOTE: The language must be for the user of Role Customer. Otherwise the
	 * glass would break Ensure that the userid passed is the userid of the
	 * customer
	 * 
	 * @param conn
	 * @param languageCd
	 * @param userCd
	 * @return
	 * @throws SQLException
	 */
	public Set<Section> questionDAOMethod(Connection connection,String languageCd, String userCd) throws SQLException {
		Connection conn = null;
		Set<Section> sectionSet = null;
		Set<Question> questionSet = null;
		Set<Attribute> attributeSet = null;
		TreeSet<Section> copy = new TreeSet<Section>();
		try{
			
			ModelUtils.initializeDBtoObjectModelMapping(new MortgageApplication());
			Map<String,String> m = ModelUtils.getDbAttributeToObjectNamesMap();
			System.err.println("SIZE "+m.size()+ "CONTENT"+m.toString());
			
			conn = connection;
			conn.setAutoCommit(true);
			languageCd = getLanguageCdDAO(conn,languageCd,userCd);
			sectionSet = getSectionDAO(conn,languageCd);
			questionSet = getQuestionDAO(conn,languageCd,sectionSet);
			attributeSet = getAttributeDAO(conn,languageCd,questionSet);
			getLOVDAO(conn,languageCd,attributeSet);
			
			Iterator <Section> si = sectionSet.iterator();
			
			while(si.hasNext()){
				Section s = si.next();
				if(s.getSectionLevel() == 1){
					//Only top level sections here
					copy.add(s);
				}
			}
			
			
			Iterator<Question> qi =  questionSet.iterator();
			while(qi.hasNext()){
				Question qqi = qi.next();
				//System.out.println("QUESTION ID:"+qqi.getQuestionId()+"Question DESC:"+qqi.getQuestionLongDesc()+"No of attributes:"+qqi.getAttributes().size());
				//System.out.println(qqi.toString());
			}
			Iterator<Attribute> ai =  attributeSet.iterator();
			while(ai.hasNext()){
				Attribute aai = ai.next();
				//System.out.println("QUESTION ID:"+qqi.getQuestionId()+"Question DESC:"+qqi.getQuestionLongDesc()+"No of attributes:"+qqi.getAttributes().size());
				//System.out.println(aai.toString());
				System.out.println(aai.getObjectAttrFQN());
			}
			

		
		}

		catch (Exception ee) {

			throw new SQLException("SQL Error - See stack-trace for details", ee);
		}

		return copy;

	}
	
	public Set<Section> questionDAOMethod(String languageCd, String userCd) throws SQLException{
		Set<Section> s = null;
		try{
			Connection conn = DatabaseService.getConnection();
			s = questionDAOMethod(conn,languageCd,userCd);
		}catch (Exception e){
			throw new SQLException(e);
		}
		return s;
	}
	

	public Set<Section> timelineDAOMethod(Connection connection,String languageCd, String userCd) throws SQLException {
		Connection conn = null;
		Set<Section> sectionSet = null;
	
		try{
			
			conn = connection;
			conn.setAutoCommit(true);
			languageCd = getLanguageCdDAO(conn,languageCd,userCd);
			sectionSet = getSectionDAO(conn,languageCd);
			Iterator <Section> si = sectionSet.iterator();
			while(si.hasNext()){
				Section s = si.next();
				//System.out.println(s.toString());
			}
		
		}

		catch (Exception ee) {

			throw new SQLException("SQL Error - See stack-trace for details", ee);
		}

		return sectionSet;
	}
		
	public Set<Section> timelineDAOMethod(String languageCd, String userCd) throws SQLException{
		Set<Section> s = null;
		try{
			Connection conn = DatabaseService.getConnection();
			s = timelineDAOMethod(conn,languageCd,userCd);
		}catch (Exception e){
			throw new SQLException(e);
		}
		return s;
	}
	

	/**
	 * Finds and returns an object from a given set
	 * 
	 * @param objToFind
	 * @param setToSearch
	 * @return
	 */
	public static Section findSectionInSet(Section objToFind, Set<Section> setToSearch) {
		Section foundObject = null;
		Iterator<Section> it = setToSearch.iterator();
		while (it.hasNext()) {
			Section o = it.next();
			if (o.equals(objToFind)) {
				foundObject = o;
			}

		}
		return foundObject;
	}

	public static Question findQuestionInSet(Question questionToFind, Set<Question> setToSearch) {
		Question foundObject = null;
		Iterator<Question> it = setToSearch.iterator();
		while (it.hasNext()) {
			Question o = it.next();
			if (o.equals(questionToFind)) {
				foundObject = o;
			}

		}
		return foundObject;
	}

	public static void computeChildrenForQuestion(Set<Question> questionSet) {

		Iterator<Question> childIterator = questionSet.iterator();
		while (childIterator.hasNext()) {
			Question child = childIterator.next();
			int parentQuestionId = child.getParentQuestionId();
			Question parentStub = new Question();
			parentStub.setQuestionId(parentQuestionId);
			if (parentQuestionId <= 0) {
				// Top level question
			} else {
				// I do have a parent - I need to add myself to parent
				Question parentQuestion = findQuestionInSet(parentStub,questionSet);
				parentQuestion.addChildQuestion(child);
				// System.out.println("Parent"+parent.getSectionId()+"
				// child:"+child.getSectionId());

			}
		}

	}

	/**
	 * Computes the children and depth
	 */
	public static void computeChildrenAndLevelsForSection(Set<Section> sectionSet) {

		Iterator<Section> childIterator = sectionSet.iterator();
		while (childIterator.hasNext()) {
			Section child = childIterator.next();
			int parentSectionId = child.getParentSectionId();
			
			Section parentStub = new Section();
			parentStub.setSectionId(parentSectionId);
			if (parentSectionId <=0 ) {
				// I don't have a parent. I am at level 1
				child.setSectionLevel(1);
				// System.out.println("No Parent"+"
				// child:"+child.getSectionId());
			} else {
				// I do have a parent - I need to add myself to parent
				Section parent = findSectionInSet(parentStub,sectionSet);
				parent.addChildSection(child);
				//System.out.println("Parent"+parent.getSectionId()+" child:"+child.getSectionId());

			}
		}

		// When I am here all parents at level 1 have a level set as 1 however
		// the children have not.
		// Myself and my siblings are now attached to a parent

		boolean completed = false;
		// Task will be completed when all sections will have a level associated
		// with it
		int currentLevel = 1;

		while (!completed) {

			// System.out.println("Currently in level"+currentLevel);
			Iterator<Section> levelIterator = sectionSet.iterator();

			while (levelIterator.hasNext()) {
				Section levelSetSection = levelIterator.next();
				int parentSetSectionId = levelSetSection.getParentSectionId();

				
				if ((parentSetSectionId > 0)) {

					Section parentSetSectionStub = new Section();
					parentSetSectionStub.setSectionId(parentSetSectionId);
					Section parentSetSection = findSectionInSet(parentSetSectionStub,sectionSet);
					//System.out.println("PARENT SET SECTION ID" +parentSetSectionId);
					
					if ((parentSetSection.getSectionLevel() == currentLevel)) {

							levelSetSection.setSectionLevel(currentLevel + 1);

					} else {


					}
				}
				// Let's print out section_id and level
				//System.out.println("Section Id:"+levelSetSection.getSectionId()+" Level:"+levelSetSection.getSectionLevel());

			}

			
			currentLevel++;

			// How to determine completed?? - This task would only complete when
			// every section has a level assigned
			Iterator<Section> checkIterator = sectionSet.iterator();
			completed = true;
			while (checkIterator.hasNext()) {
				Section checkLevelNo = checkIterator.next();
				if (checkLevelNo.getSectionLevel() == -1) {
					// We still have a -1 not completed
					completed = false;
					break;
				}
			}

			/*
			Iterator<Section> p = sectionSet.iterator();
			while (p.hasNext()) {
				Section ps = p.next();
				// System.out.print("My id is"+ps.getSectionId()+" Some of us
				// need to be at level 1: :"+ps.getSectionLevel());
				if (ps.getParentSection() == null) {
					// System.out.println(" I dont have a parent");;
				} else {
					// System.out.println(" Myparent is at
					// "+ps.getParentSection().getSectionLevel()+" level with id
					// "+ps.getParentSection().getSectionId());
				}
				*/

			}

		}

	

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public static void main(String[] args) throws Exception {
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE", "mortgage",
					"password");
			QuestionMetaDataDAOImpl qdao = new QuestionMetaDataDAOImpl();
			
			
		//Test case 1 - user with English and requests default	
		Set<Section> s	= qdao.questionDAOMethod(connection,"default", "123456");
		//Test Case 2 - user with English and requests en
		//Set<Section> s	= qdao.questionDAOMethod(connection,"en", "123456");
		//Test case 3 - user with English and requests spanish
		//Set<Section> s	= qdao.questionDAOMethod(connection,"es", "123456");
		//Test case 4 - user with spanish and requests default
		//Set<Section> s	= qdao.questionDAOMethod(connection,"default", "123458");
		//Test case 5 - user with spanish and requests english
		//Set<Section> s	= qdao.questionDAOMethod(connection,"en", "123458");
		//Test case 6 - user with spanish and requests spanish
		//Set<Section> s	= qdao.questionDAOMethod(connection,"es", "123458");
		//Test case 7 - requests unsupported language
		//Set<Section> s	= qdao.questionDAOMethod(connection,"bn", "123456");
		
		
		
		Iterator<Section> i = s.iterator();
		while(i.hasNext()){
			Section ss = i.next();
			//System.out.println(ss.toString());
		}
			
			
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}
		

		
			
	}

	
	public Timeline getTimeline(TimelineRequest timelineRequest) throws SQLException{
		Timeline timeline = null;
		try{
			Connection conn = DatabaseService.getConnection();
			timeline = getTimeline(conn,timelineRequest);
		}catch (Exception e){
			throw new SQLException(e);
		}
		return timeline;
	}

	public Timeline getTimeline(Connection conn, TimelineRequest timelineRequest) throws SQLException{
		Timeline timeline = null;
		try{
			
			
			
		}catch (Exception e){
			throw new SQLException(e);
		}
		return timeline;
	}
	
}
