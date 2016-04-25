package com.jpmorgan.awm.pb.mortgageorigination.dao;

import java.sql.SQLException;
import java.util.List;

import com.jpmorgan.awm.pb.mortgageorigination.request.MortgageApplicationRequest;
import com.jpmorgan.awm.pb.mortgageorigination.response.MortgageApplicationResponse;
import com.jpmorgan.awm.pb.mortgageorigination.response.SaveMortgageApplicationResponse;

public interface MortgageDAO {

	public SaveMortgageApplicationResponse saveMortgageDetails(MortgageApplicationRequest mortgageApplicationRequest)
			throws SQLException;

	public List<MortgageApplicationResponse> getMortgageDetails(String clientOrAdvisor, long clientOrAdvisorPartyId);

}
