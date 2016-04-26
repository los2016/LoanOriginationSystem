package com.jpmorgan.awm.pb.mortgageorigination.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import com.jpmorgan.awm.pb.mortgageorigination.dao.QuestionMetaDataDAO;
import com.jpmorgan.awm.pb.mortgageorigination.dao.UserDAO;
import com.jpmorgan.awm.pb.mortgageorigination.request.MortgageApplicationRequest;
import com.jpmorgan.awm.pb.mortgageorigination.response.CoverageResponse;
import com.jpmorgan.awm.pb.mortgageorigination.response.MortgageApplicationResponse;
import com.jpmorgan.awm.pb.mortgageorigination.response.SaveMortgageApplicationResponse;
import com.jpmorgan.awm.pb.mortgageorigination.response.UserDetailsResponse;
import com.jpmorgan.awm.pb.mortgageorigination.service.QNAServices;
import com.myorg.losmodel.model.LOSResponse;
import com.myorg.losmodel.model.ValidateQuestionRequest;
import com.myorg.losmodel.model.ValidateQuestionResponse;
import com.myorg.losmodel.model.questions.Section;

@RestController
public class MortgageRestController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private MortgageDAO mortgageDAO;

	@Autowired
	private CoverageDAO coverageAO;

	@Autowired
	private QNAServices qnaServices;

	@Autowired
	private QuestionMetaDataDAO questionMetaData;

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
	public ResponseEntity<SaveMortgageApplicationResponse> saveMortgageApplication(
			@RequestBody MortgageApplicationRequest mortgageApplicationRequest) {

		// TODo code for calling Dao Layer

		SaveMortgageApplicationResponse mortgageApplicationResponse = new SaveMortgageApplicationResponse();
		try {
			mortgageApplicationResponse = mortgageDAO.saveMortgageDetails(mortgageApplicationRequest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOSResponse response = new LOSResponse();
			response.setReturnMsg("Application Save Failed");
			response.setReturnType("Error");
			mortgageApplicationResponse.setResponse(response);
			mortgageApplicationResponse.setMortgageId(-1);
		}

		return new ResponseEntity<SaveMortgageApplicationResponse>(mortgageApplicationResponse, HttpStatus.OK);

	}

	@RequestMapping(value = "/getMortgageApplicationsDetails", method = RequestMethod.GET)
	public ResponseEntity<List<MortgageApplicationResponse>> getMortgageApplicationsDetails(
			@RequestParam String clientOrAdvisor, @RequestParam long clientOrAdvisorPartyId) {

		List<MortgageApplicationResponse> mortgageApplicationList = mortgageDAO.getMortgageDetails(clientOrAdvisor,
				clientOrAdvisorPartyId);

		return new ResponseEntity<List<MortgageApplicationResponse>>(mortgageApplicationList, HttpStatus.OK);

	}

	@RequestMapping(value = "/validateQuestion", method = RequestMethod.POST)
	public ResponseEntity<ValidateQuestionResponse> validateQuestion(@RequestBody ValidateQuestionRequest request) {

		ValidateQuestionResponse resp = qnaServices.validateQuestion(request);

		return new ResponseEntity<ValidateQuestionResponse>(resp, HttpStatus.OK);

	}

	@RequestMapping(value = "/getMortgageQuestionsMetaData", method = RequestMethod.GET)
	public ResponseEntity<Set<Section>> getMortgageQuestionsMetaData(@RequestParam String languageCd,
			@RequestParam String userCode) {

		Set<Section> sections = new TreeSet<Section>();
		try {
			sections = questionMetaData.questionDAOMethod(languageCd, userCode);
		} catch (SQLException e) {
			LOSResponse response = new LOSResponse();
			response.setReturnMsg("Application Failed to Fetch getMortgageQuestionsMetaData");
			response.setReturnType("Error");
			e.printStackTrace();
		}
		return new ResponseEntity<Set<Section>>(sections, HttpStatus.OK);

	}

}
