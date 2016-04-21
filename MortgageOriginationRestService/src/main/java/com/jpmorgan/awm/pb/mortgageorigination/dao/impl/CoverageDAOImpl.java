package com.jpmorgan.awm.pb.mortgageorigination.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jpmorgan.awm.pb.mortgageorigination.dao.CoverageDAO;
import com.jpmorgan.awm.pb.mortgageorigination.response.CoverageResponse;

@Service
public class CoverageDAOImpl implements CoverageDAO {

	public CoverageResponse getClientOrAdvisorCoverage(String userType, String partyId) {
		CoverageResponse coverageResponse = new CoverageResponse();

		// TODO need to replace this logic with Database call

		List<Long> coverageList = new ArrayList<Long>();
		coverageList.add(new Long(2341434l));
		coverageList.add(new Long(5678568l));
		coverageList.add(new Long(78782334l));
		coverageList.add(new Long(99234134l));
		coverageResponse.setCoverageList(coverageList);
		return coverageResponse;
	}

}
