package com.jpmorgan.awm.pb.mortgageorigination.dao;

import java.sql.SQLException;
import java.util.List;

import com.jpmorgan.awm.pb.mortgageorigination.request.MortgageApplicationRequest;
import com.jpmorgan.awm.pb.mortgageorigination.response.MortgageApplicationResponse;

public interface MortgageDAO {

	public MortgageApplicationResponse saveMortgageDetails(MortgageApplicationRequest mortgageApplicationRequest)
			throws SQLException;

	public List<MortgageApplicationResponse> getMortgageDetails(String clientOrAdvisor, long clientOrAdvisorPartyId);

}
