package com.jpmorgan.awm.pb.mortgageorigination.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
import com.jpmorgan.awm.pb.mortgageorigination.response.UserDetailsResponse;
import com.jpmorgan.awm.pb.mortgageorigination.service.QNAServices;
import com.myorg.losmodel.model.LOSResponse;
import com.myorg.losmodel.model.TimelineRequest;
import com.myorg.losmodel.model.ValidateQuestionRequest;
import com.myorg.losmodel.model.ValidateQuestionResponse;
import com.myorg.losmodel.model.client.BPMResponse;
import com.myorg.losmodel.model.client.BPMTask;
import com.myorg.losmodel.model.client.Sections;
import com.myorg.losmodel.model.client.Timeline;
import com.myorg.losmodel.model.questions.Section;
import com.myorg.losworkflow.services.WorkflowRemoteService;
import com.myorg.losworkflow.services.WorkflowRemoteServiceImpl;

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

	@RequestMapping(value = "/validateQuestion", method = RequestMethod.POST)
	public ResponseEntity<ValidateQuestionResponse> validateQuestion(@RequestBody ValidateQuestionRequest request) {

		ValidateQuestionResponse resp = qnaServices.validateQuestion(request);

		return new ResponseEntity<ValidateQuestionResponse>(resp, HttpStatus.OK);

	}

	
	@RequestMapping(value = "/getTimeline", method = RequestMethod.POST)
	public ResponseEntity<Timeline> getTimeline(@RequestBody TimelineRequest timelineRequest) {
		Timeline timeline = null;
		try {
			timeline = questionMetaData.getTimeline(timelineRequest);
			

		} catch (SQLException e) {
			LOSResponse response = new LOSResponse();
			response.setReturnMsg("Application Failed to Fetch getMortgageQuestionsMetaData");
			response.setReturnType("Error");
			e.printStackTrace();
		}
		return new ResponseEntity<Timeline>(timeline, HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/getMortgageQuestionsMetaData", method = RequestMethod.GET)
	public ResponseEntity<Sections> getMortgageQuestionsMetaData(@RequestParam String languageCd,
			@RequestParam String userCode) {

		Sections sections = new Sections();
		try {
			Set<Section> sectionSet = questionMetaData.questionDAOMethod(languageCd, userCode);
			sections.setSections(sectionSet);
		} catch (SQLException e) {
			LOSResponse response = new LOSResponse();
			response.setReturnMsg("Application Failed to Fetch getMortgageQuestionsMetaData");
			response.setReturnType("Error");
			e.printStackTrace();
		}
		return new ResponseEntity<Sections>(sections, HttpStatus.OK);

	}
	
	/**
	 * Start BPM work flow
	 * @return
	 */
	@RequestMapping(value = "/startWorkflow", method = RequestMethod.GET)
	public ResponseEntity<BPMResponse> startWorkflow() {
		BPMResponse bpmResponse = new BPMResponse();
		try {
			//Using dummy implementation for demo
			//Should use	 -- public WorkflowRemoteServiceImpl(String deploymentId, String baseUrl, String user, String password, String processName)
			WorkflowRemoteService workflowRemoteService = new WorkflowRemoteServiceImpl();
			
			//Start Process
			Map<String, String> startProcessResultMap = workflowRemoteService.startWorkflow();
			long bpmProcessId = Long.parseLong(startProcessResultMap.get("bpmProcessId"));
			System.out.println(startProcessResultMap);

			//First task group will get started as part of work flow startup - so its ok if "Apply" task is not explicitly called.
			//JBPM user names are hard coded for demo
			Map<String, String> transitionResultMap1 = workflowRemoteService.transitionToNewTask(bpmProcessId, "krisv", null, "Property Address");
			System.out.println(transitionResultMap1);
			
			bpmResponse.setBpmProcessId(bpmProcessId);
			bpmResponse.setReturnMessage("Process started");
			bpmResponse.setReturnType("Success");
			
		} catch (Exception e) {
			LOSResponse response = new LOSResponse();
			response.setReturnMsg("Application Failed to start BPM workflow");
			response.setReturnType("Error");
			e.printStackTrace();
		}
		return new ResponseEntity<BPMResponse>(bpmResponse, HttpStatus.OK);

	}
	
	/**
	 * Transition to new task 
	 * Use this method in  two instance : 
	 * 1) if user randomly moves through different screens within one section. In that case case user has to give current task name and new task name 
	 * 2) If current task is complete, that would  be passed as null. and transition New task name
	 * @param bpmProcessId
	 * @param userId
	 * @param currentTaskName
	 * @param newTaskName
	 * @return
	 */
	@RequestMapping(value = "/transitionToNewTask", method = RequestMethod.GET)
	public ResponseEntity<BPMResponse> transitionToNewTask(@RequestParam long bpmProcessId,
			@RequestParam String userId, @RequestParam String currentTaskName, @RequestParam String newTaskName) {
		BPMResponse bpmResponse = new BPMResponse();
		try {
			WorkflowRemoteService workflowRemoteService = new WorkflowRemoteServiceImpl();
			
			//User name is hard coded for demo
			Map<String, String> transitionResultMap = workflowRemoteService.transitionToNewTask(bpmProcessId, "krisv", currentTaskName, newTaskName);
			
			bpmResponse.setBpmProcessId(bpmProcessId);
			bpmResponse.setReturnMessage(transitionResultMap.get("returnMessage"));
			bpmResponse.setReturnType(transitionResultMap.get("returnType"));
			
		} catch (Exception e) {
			LOSResponse response = new LOSResponse();
			response.setReturnMsg("Application Failed to transition BPM workflow from " + currentTaskName + " to " + newTaskName );
			response.setReturnType("Error");
			e.printStackTrace();
		}
		return new ResponseEntity<BPMResponse>(bpmResponse, HttpStatus.OK);

	}
	
	/**
	 * Mark task as complete
	 * @param bpmProcessId
	 * @param userId
	 * @param currentTaskName
	 * @return
	 */
	@RequestMapping(value = "/markTaskAsComplete", method = RequestMethod.GET)
	public ResponseEntity<BPMResponse> markTaskAsComplete(@RequestParam long bpmProcessId,
			@RequestParam String userId, @RequestParam String currentTaskName) {
		BPMResponse bpmResponse = new BPMResponse();
		try {
			WorkflowRemoteService workflowRemoteService = new WorkflowRemoteServiceImpl();
			
			//User name is hard coded for demo
			Map<String, String> transitionResultMap = workflowRemoteService.markTaskAsComplete(bpmProcessId, "krisv", currentTaskName);
			
			bpmResponse.setBpmProcessId(bpmProcessId);
			bpmResponse.setReturnMessage(transitionResultMap.get("returnMessage"));
			bpmResponse.setReturnType(transitionResultMap.get("returnType"));
			
		} catch (Exception e) {
			LOSResponse response = new LOSResponse();
			response.setReturnMsg("Application Failed to complete BPM task " + currentTaskName);
			response.setReturnType("Error");
			e.printStackTrace();
		}
		return new ResponseEntity<BPMResponse>(bpmResponse, HttpStatus.OK);

	}
	
	/**
	 * Get task status from work flow. For demo, getTaksStatusForProcessInstance returns data from a static variable which 
	 * updates variable value as per transition / completion calls
	 * @param bpmProcessId
	 * @return
	 */
	@RequestMapping(value = "/getTaksStatusForProcessInstance", method = RequestMethod.GET)
	public ResponseEntity<BPMResponse> getTaksStatusForProcessInstance(@RequestParam long bpmProcessId) {
		BPMResponse bpmResponse = new BPMResponse();
		try {
			WorkflowRemoteService workflowRemoteService = new WorkflowRemoteServiceImpl();
			
			//User name is hard coded for demo
			Map<String, String> taskStatusMap = workflowRemoteService.getTaksStatusForProcessInstance(bpmProcessId);
			
			bpmResponse.setBpmProcessId(bpmProcessId);
			bpmResponse.setReturnMessage(taskStatusMap.get("returnMessage"));
			bpmResponse.setReturnType(taskStatusMap.get("returnType"));
			
			for(String key : taskStatusMap.keySet()) {
				if(null != key && !key.equals("bpmProcessId") && !key.equals("returnType")){
					bpmResponse.getTasks().add(new BPMTask(key, taskStatusMap.get(key)));
				}
			}
			
		} catch (Exception e) {
			LOSResponse response = new LOSResponse();
			response.setReturnMsg("Application Failed to get BPM task statuses for instance " + bpmProcessId);
			response.setReturnType("Error");
			e.printStackTrace();
		}
		return new ResponseEntity<BPMResponse>(bpmResponse, HttpStatus.OK);

	}

}
