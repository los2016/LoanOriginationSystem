package com.jpmorgan.awm.pb.mortgageorigination.dao;

import com.jpmorgan.awm.pb.mortgageorigination.response.CoverageResponse;

public interface CoverageDAO {

	public CoverageResponse getClientOrAdvisorCoverage(String userType, String partyId);

}
