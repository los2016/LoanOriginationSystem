package com.jpmorgan.awm.pb.mortgageorigination.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.jpmorgan.awm.pb.mortgageorigination.dao.MortgageDAO;
import com.jpmorgan.awm.pb.mortgageorigination.request.MortgageApplicationRequest;
import com.jpmorgan.awm.pb.mortgageorigination.response.MortgageApplicationResponse;
import com.myorg.losmodel.model.LOSResponse;
import com.myorg.losmodel.model.client.MortgageApplication;

@Service
public class MortgageDAOImpl implements MortgageDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public MortgageApplicationResponse saveMortgageDetails(MortgageApplicationRequest mortgageApplicationRequest)
			throws SQLException {
		MortgageApplicationResponse mortgageApplicationResponse = new MortgageApplicationResponse();

		// TODO Need to replace DAO logic once we will have Query from
		// Shubhrajit for Save Mortgage Application

		LOSResponse response = new LOSResponse();
		response.setReturnMsg("Application Saved Sucessfully");
		response.setReturnType("Success");
		mortgageApplicationResponse.setResponse(response);
		mortgageApplicationResponse.setMortgageId(23444345243l);

		return mortgageApplicationResponse;
	}

	public List<MortgageApplicationResponse> getMortgageDetails(String clientOrAdvisor, long clientOrAdvisorPartyId) {
		// TODO Need to replace DAO logic to get Mortgage applications from
		// database as JSON String.

		List<MortgageApplicationResponse> mortgageApplicationsList = new ArrayList<MortgageApplicationResponse>();
		MortgageApplication mortgageApplication = new MortgageApplication();
		MortgageApplicationResponse mortgageApplicationResponse = new MortgageApplicationResponse();
		mortgageApplicationResponse.setMortgageApplication(mortgageApplication);
		LOSResponse response = new LOSResponse();
		response.setReturnMsg("List of Applications");
		response.setReturnType("Success");
		mortgageApplicationResponse.setResponse(response);
		mortgageApplicationsList.add(mortgageApplicationResponse);
		return mortgageApplicationsList;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
