package com.jpmorgan.awm.pb.mortgageorigination.dao;

import java.sql.SQLException;

import com.jpmorgan.awm.pb.mortgageorigination.request.MortgageApplicationRequest;
import com.jpmorgan.awm.pb.mortgageorigination.response.MortgageApplicationResponse;
import com.jpmorgan.awm.pb.mortgageorigination.response.SaveMortgageApplicationResponse;

public interface MortgageDAO {

	public SaveMortgageApplicationResponse saveMortgageDetails(MortgageApplicationRequest mortgageApplicationRequest)
			throws SQLException;

	public MortgageApplicationResponse getMortgageDetails(String clientOrAdvisor, long clientOrAdvisorPartyId,
			long mortgageId);

}
