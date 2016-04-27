package com.jpmorgan.awm.pb.mortgageorigination.businessdelegate.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.jpmorgan.awm.pb.mortgageorigination.businessdelegate.TimeLineBusinessDelegate;
import com.jpmorgan.awm.pb.mortgageorigination.dao.CoverageDAO;
import com.jpmorgan.awm.pb.mortgageorigination.dao.MortgageDAO;
import com.jpmorgan.awm.pb.mortgageorigination.dao.QuestionMetaDataDAO;
import com.jpmorgan.awm.pb.mortgageorigination.dao.UserDAO;
import com.jpmorgan.awm.pb.mortgageorigination.dao.impl.CoverageDAOImpl;
import com.jpmorgan.awm.pb.mortgageorigination.dao.impl.MortgageDAOImpl;
import com.jpmorgan.awm.pb.mortgageorigination.dao.impl.QuestionMetaDataDAOImpl;
import com.jpmorgan.awm.pb.mortgageorigination.dao.impl.UserDAOImpl;
import com.jpmorgan.awm.pb.mortgageorigination.response.CoverageResponse;
import com.jpmorgan.awm.pb.mortgageorigination.response.MortgageApplicationResponse;
import com.myorg.losmodel.model.User;
import com.myorg.losmodel.model.client.MortgageApplication;
import com.myorg.losmodel.model.questions.Section;
import com.myorg.losmodel.model.questions.Timeline;
import com.myorg.losmodel.model.questions.TimelineElement;
import com.myorg.losmodel.model.questions.TimelineRequest;
import com.myorg.losmodel.model.questions.TimelineSectionWrapper;
import com.myorg.losmodel.model.questions.TimelineSectionWrapperComparator;



public class TimeLineBusinessDelegateImpl implements TimeLineBusinessDelegate{
	
	//@Autowired
	private QuestionMetaDataDAO questionMetaData;
	
	//@Autowired
	private UserDAO userDAO;

	//@Autowired
	private CoverageDAO coverageDAO;
	
	
	public CoverageDAO getCoverageDAO() {
		return coverageDAO;
	}

	public void setCoverageDAO(CoverageDAO coverageDAO) {
		this.coverageDAO = coverageDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public QuestionMetaDataDAO getQuestionMetaData() {
		return questionMetaData;
	}
	
	private MortgageDAO mortgageDAO;
	

	public MortgageDAO getMortgageDAO() {
		return mortgageDAO;
	}

	public void setMortgageDAO(MortgageDAO mortgageDAO) {
		this.mortgageDAO = mortgageDAO;
	}

	public void setQuestionMetaData(QuestionMetaDataDAO questionMetaData) {
		this.questionMetaData = questionMetaData;
	}

	public Timeline getTimeline(TimelineRequest timelineRequest, MortgageDAO mdao) throws Exception{
		System.out.println("INSIDE TIMELINE REQUEST BD");
		
		this.mortgageDAO = mdao;
		
		Timeline timeline = new Timeline();
		Set<Section> sectionSet = null;
		Set<TimelineElement> timelineElementSet = new TreeSet<TimelineElement>();
		Set<TimelineSectionWrapper> timelineSectionSet = null;
		
		try{
			if(mortgageDAO == null){
				System.err.println("AUTO WIRING OF Mortgage DAO FAILED IN CLASS TimeLineBusinessDelegageImpl ");
				mortgageDAO = new MortgageDAOImpl();
				
			}
			
			if(questionMetaData == null){
				System.err.println("AUTO WIRING OF QUESTION META DATA FAILED IN CLASS TimeLineBusinessDelegageImpl ");
				questionMetaData = new QuestionMetaDataDAOImpl();
				
			}
			
			if(userDAO == null){
				System.err.println("AUTO WIRING OF UserDAO FAILED IN CLASS TimeLineBusinessDelegageImpl ");
				userDAO = new UserDAOImpl();
				
			}
			
			if(coverageDAO == null){
				System.err.println("AUTO WIRING OF UserDAO FAILED IN CLASS TimeLineBusinessDelegageImpl ");
				coverageDAO = new CoverageDAOImpl();
				
			}
			
			System.out.println("I have got all the DAOs");
			String userCd = timelineRequest.getUserCode();
			//if(userCd == null){
				//System.out.println("USER CODE IS NULL hardcoding for test");
				//userCd = "123456";
			//}
			
			System.out.println("USER CODE IS "+userCd);

			User userInitial = questionMetaData.getUserDetails(userCd);
			
			System.out.println("User object found with Role:"+userInitial.getRoleId());
			
			ArrayList<String> partyIds = new ArrayList<String>();
			int userRole = userInitial.getRoleId();
			
			if (userRole == 2000){
				System.out.println("User is a advisor. Need to get his clients");
				CoverageResponse c =  coverageDAO.getClientOrAdvisorCoverage("A", Long.toString(userInitial.getPartyId()));
				System.out.println("Got coverage info "+c);
				List<Long> partyIdLong = c.getCoverageList();
				Iterator<Long> i = partyIdLong.iterator();
				while(i.hasNext()){
					long l = i.next();
					partyIds.add(Long.toString(l));
				}
				
			}else{
				System.out.println("User is a client with party id"+userInitial.getPartyId());
				partyIds.add(Long.toString(userInitial.getPartyId()));
			}
			
			List<String> mortgageIds = timelineRequest.getMortgageId();
			Iterator<String> it = mortgageIds.iterator();
			
			while(it.hasNext()){
				long mortgageId = -1l;
				String mortgageIdStr = it.next();
				System.out.println("MORTGAGE ID FROM JSON IS "+mortgageIdStr);
				if("ALL".equalsIgnoreCase(mortgageIdStr)){
					System.out.println("ALL REQUESTED - SET TO 0");
					mortgageId = 0l;
					
				}else{
					try{
						mortgageId = Long.parseLong(mortgageIdStr);
					}catch(NumberFormatException ne){
						System.err.println("NUMBER FORMAT EXCEPTION. MORTGAGE ID "+mortgageIdStr+" CANNOT BE PARSED TO A LONG VALUE");
						throw new Exception(ne);
					}
				}
				
				//When I am here, I should have a bunch of mortgage ids and party ids
				Iterator<String> partyIt = partyIds.iterator();
				while(partyIt.hasNext()){
					String partyId = partyIt.next();
					System.out.println("Trying to get details with Mortgage ID "+mortgageId+" and party Id :"+partyId );
					MortgageApplicationResponse getMortgageDetails = mortgageDAO.getMortgageDetails("C", Long.parseLong(partyId), mortgageId);
					List<MortgageApplication> l = getMortgageDetails.getMortgageApplications();
					System.out.println("NO OF MORTGAGE APPLICATION OBJECTS RECEIVED: "+l.size());
					Iterator<MortgageApplication> maIt = l.iterator();
					while(maIt.hasNext()){
						
						System.out.println("ITERATING ... YOU SHOULD SEE THIS "+l.size()+" TIMES");
						MortgageApplication m = maIt.next();
						TimelineElement telement = new TimelineElement();
						telement.setBpmProcessId(m.getBpmProcessId());
						
						
						
						telement.setMortgageId(mortgageId);
						timelineElementSet.add(telement);
						
					}
					
				}
				
					
								
					
					
						
					
							
				
			}
			
			System.out.println("When I am here - I have created a set of timeline elements and getting the section information");
			
			sectionSet = questionMetaData.getTimelineSections(timelineRequest);
			timelineSectionSet = buildTimelineSection(sectionSet);
			//timeline.setSections(timelineSectionSet);
			//At this point we got the ids
			
			
			System.out.println("Each timeline element will have its own copy of the section set // before we can set status");
			
			
			Set<TimelineSectionWrapper> copiedSet = new TreeSet<TimelineSectionWrapper>(new TimelineSectionWrapperComparator());
			

			
			Iterator<TimelineElement> tit = timelineElementSet.iterator();
			while (tit.hasNext()){
				TimelineElement el = tit.next();
				System.out.println("TIMELINE ELEMENT WITH MORTGAGE ID - "+el.getMortgageId());
				Iterator<TimelineSectionWrapper> tswIt = timelineSectionSet.iterator();
				while(tswIt.hasNext()){
					TimelineSectionWrapper x = tswIt.next();
					TimelineSectionWrapper s = new TimelineSectionWrapper();
					s.setMortgageId(el.getMortgageId());
					copyTimelineSection(x,s);
					copiedSet.add(s);
					System.out.println("FOR MORTGAGE "+el.getMortgageId()+"WE CREATED SET "+s.getSectionId()+" NAME:"+s.getPresentSectionNm());
					Iterator<TimelineSectionWrapper> childIt = x.getChildSections().iterator();
					while(childIt.hasNext()){
						TimelineSectionWrapper child = new TimelineSectionWrapper();
						child.setMortgageId(el.getMortgageId());
						copyTimelineSection(childIt.next(),child);
						s.addChildSection(child);
						
					}
					System.out.println("FOR MORTGAGE "+el.getMortgageId()+"WE CREATED SET "+s.getSectionId()+
							" NAME:"+s.getPresentSectionNm()+"NO OF CHILDREN: "+s.getChildSections().size());
					
						
					
					
				}
				System.out.println("PRINTING OUT THE COPIED SET");
				Iterator<TimelineSectionWrapper> z = copiedSet.iterator();
				while(z.hasNext()){
					TimelineSectionWrapper tlsw = z.next();
					System.out.println("ID "+tlsw.getSectionId()+" NAME "+tlsw.getPresentSectionNm()+ " NO OF CHILDREN "+tlsw.getChildSections().size());
				}
				
				
				
			}
			
			//At this stage, we have a set of sections for each mortgage Id (Timeline element - but not added to timeline objects yet
			System.out.println("NO OF ELEMENTS IN TIMELINE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+timelineElementSet.size());
			Iterator<TimelineElement> tit2 = timelineElementSet.iterator();
			while(tit2.hasNext()){
				System.out.println("ITERATING INSIDE _> SEE THIS"+timelineElementSet.size()+" TIMES");
				System.out.println("SIZE OF COPIED SET SHOULD BE SAME AS THE NO OF ITEMS IN DB - SIZE"+copiedSet.size());
				Iterator<TimelineSectionWrapper> sit = copiedSet.iterator();
				TimelineElement tele = tit2.next();
				while(sit.hasNext()){
					TimelineSectionWrapper wrapper = sit.next();
					System.out.println("WRAPPER LEVEL "+wrapper.getSectionLevel()+" WRAPPER MORTGAGE ID"+wrapper.getMortgageId()
					+ " TELE MORTGAGE ID "+tele.getMortgageId());
					
					if((wrapper.getSectionLevel() == 1)&&(tele.getMortgageId() == wrapper.getMortgageId())){
						tele.addSection(wrapper);
						System.out.println("ADDING LEVEL 1 ELELMENT "+wrapper.getSectionId()+" NAME "+wrapper.getPresentSectionNm()
						+" NO OF CHILDREN: "+wrapper.getChildSections().size()+" TO ELEMENT "+tele.getMortgageId());
					}
				}
			}
			
			//All computed - add to timeline
			
			timeline.setTimelineElement(timelineElementSet);
			
			//At this point - need to iterate and get the process states
			
			Iterator<TimelineElement> tit3 = timelineElementSet.iterator();
			while(tit3.hasNext()){
				TimelineElement elem = tit3.next();
				System.out.println(" CALLING JBPM WITH PROCESS ID "+elem.getBpmProcessId()+" FOR MORTGAGE "+elem.getMortgageId());
				//Need to iterate through all the states and call - expensive call - think how to make this compact
			}
			
			
		}catch(Exception e){
			throw new Exception("EXCEPTION IN TIMELINE BUSINESS DELEGATE - ROOT CAUSE ",e);
		}
		return timeline;
	}
	
	public void copyTimelineSection(TimelineSectionWrapper source,TimelineSectionWrapper target){
		target.setSectionId(source.getSectionId());
		target.setParentSectionId(source.getParentSectionId());
		target.setSectionLevel(source.getSectionLevel());
		target.setPresentSectionNm(source.getPresentSectionNm());
		target.setPastSectionNm(source.getPastSectionNm());
		target.setFutureSectionNm(source.getFutureSectionNm());
		target.setSequenceNo(source.getSequenceNo());

	}
	
	public TimelineSectionWrapper buildTimelineSection(Section s){
		System.out.println("MAPPING "+s.getSectionId());
		TimelineSectionWrapper wrapper = new TimelineSectionWrapper();
		wrapper.setSectionId(s.getSectionId());
		wrapper.setParentSectionId(s.getParentSectionId());
		wrapper.setSectionLevel(s.getSectionLevel());
		wrapper.setPresentSectionNm(s.getPresentSectionNm());
		wrapper.setPastSectionNm(s.getPastSectionNm());
		wrapper.setFutureSectionNm(s.getFutureSectionNm());
		wrapper.setSequenceNo(s.getSequenceNo());
		wrapper.setMortgageId(-1l);
		
		Set<Section> childSections = s.getChildSections();
		Iterator<Section> children = childSections.iterator();
		while(children.hasNext()){
			Section child = children.next();
			TimelineSectionWrapper childWrapper = buildTimelineSection(child);
			wrapper.addChildSection(childWrapper);
			
		}
		
		return wrapper;
	}
	
	
	public Set<TimelineSectionWrapper> buildTimelineSection(Set<Section> sectionSet){
			Set<TimelineSectionWrapper> timeSectionSet = new TreeSet<TimelineSectionWrapper>(new TimelineSectionWrapperComparator());
			Iterator<Section> i = sectionSet.iterator();
			while (i.hasNext()){
				Section s = i.next();
				TimelineSectionWrapper wrapper = buildTimelineSection(s);
				timeSectionSet.add(wrapper);
			}
			
			return timeSectionSet;
			
	}
	
	public static void main(String[] args) throws Exception{
/*
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE", "mortgage",
					"password");
			TimeLineBusinessDelegateImpl td = new TimeLineBusinessDelegateImpl();
			td.questionMetaData = new QuestionMetaDataDAOImpl();
			TimelineRequest req = new TimelineRequest();
			req.setLanguageCd("en");
			req.setUserCode("123456");
			List<String> l = new ArrayList<String>();
			l.add("14");
			l.add("21");
			req.setMortgageId(l);
			Timeline t = td.getTimeline(req, new MortgageDAO());
			Set<TimelineElement> tp = t.getTimelineElement();
			Iterator<TimelineElement> i = tp.iterator();
			while(i.hasNext()){
				TimelineElement ele = i.next();
				System.out.println(ele.getMortgageId());
				System.out.println(ele.getSections().toString());
					
			}
			
			
					
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}
		*/
	}
		
		

	

}
