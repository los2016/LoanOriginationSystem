package com.myorg.losservices.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.myorg.losmodel.model.Answer;
import com.myorg.losmodel.model.LoanInfo;
import com.myorg.losmodel.model.Question;
import com.myorg.losservices.core.QNAServices;

@Path("/losservices")
public class LOSResources {
	
	
	@Autowired
	QNAServices qnaServices;
	
	@POST
	@Path("/initialQNAList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Question> getInitialQNAList(LoanInfo loadInfo) throws Exception {
		
		List<Question> questionList = qnaServices.getInitialQNAList(loadInfo);		
		return questionList;
	}
	
	@POST
	@Path("/getDependantQNAList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Question> getDependantQNAList(List<Answer> ansList) throws Exception {
		
		List<Question> questionList = qnaServices.getDependantQNAList(ansList);		
		return questionList;
		
	}

}
