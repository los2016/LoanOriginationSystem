package com.jpmorgan.awm.pb.mortgageorigination.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpmorgan.awm.pb.mortgageorigination.dao.CoverageDAO;
import com.jpmorgan.awm.pb.mortgageorigination.dao.MortgageDAO;
import com.jpmorgan.awm.pb.mortgageorigination.dao.UserDAO;
import com.jpmorgan.awm.pb.mortgageorigination.request.MortgageApplicationRequest;
import com.jpmorgan.awm.pb.mortgageorigination.response.CoverageResponse;
import com.jpmorgan.awm.pb.mortgageorigination.response.MortgageApplicationResponse;
import com.jpmorgan.awm.pb.mortgageorigination.response.UserDetailsResponse;
import com.myorg.losmodel.model.LOSResponse;

@RestController
public class MortgageRestController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private MortgageDAO mortgageDAO;

	@Autowired
	private CoverageDAO coverageAO;

	@RequestMapping(value = "/authenticateUser", method = RequestMethod.GET)
	public ResponseEntity<UserDetailsResponse> authenticateUser(@RequestParam String userId,
			@RequestParam String password) {
		UserDetailsResponse userDetailsResponse = userDAO.authenticateUser(userId, password);
		return new ResponseEntity<UserDetailsResponse>(userDetailsResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/getClientOrAdvisorCoverage", method = RequestMethod.GET)
	public ResponseEntity<CoverageResponse> getClientOrAdvisorCoverage(@RequestParam String userType,
			@RequestParam String partyId) {
		CoverageResponse coverageResponse = coverageAO.getClientOrAdvisorCoverage(userType, partyId);

		return new ResponseEntity<CoverageResponse>(coverageResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/saveMortgageApplication", method = RequestMethod.POST)
	public ResponseEntity<MortgageApplicationResponse> saveMortgageApplication(
			@RequestBody MortgageApplicationRequest mortgageApplicationRequest) {

		// TODo code for calling Dao Layer

		MortgageApplicationResponse mortgageApplicationResponse = new MortgageApplicationResponse();
		try {
			mortgageApplicationResponse = mortgageDAO.saveMortgageDetails(mortgageApplicationRequest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOSResponse response = new LOSResponse();
			response.setReturnMsg("Application Save Failed Sucessfully");
			response.setReturnType("Error");
			mortgageApplicationResponse.setResponse(response);
			mortgageApplicationResponse.setMortgageId(23444345243l);
		}

		return new ResponseEntity<MortgageApplicationResponse>(mortgageApplicationResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/getMortgageApplicationsDetails", method = RequestMethod.GET)
	public ResponseEntity<List<MortgageApplicationResponse>> getMortgageApplicationsDetails(
			@RequestParam String clientOrAdvisor, @RequestParam long clientOrAdvisorPartyId) {

		List<MortgageApplicationResponse> mortgageApplicationList = mortgageDAO.getMortgageDetails(clientOrAdvisor,
				clientOrAdvisorPartyId);

		return new ResponseEntity<List<MortgageApplicationResponse>>(mortgageApplicationList, HttpStatus.OK);

	}

}
