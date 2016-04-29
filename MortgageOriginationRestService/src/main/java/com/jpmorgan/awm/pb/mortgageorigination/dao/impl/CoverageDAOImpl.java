package com.jpmorgan.awm.pb.mortgageorigination.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.jpmorgan.awm.pb.mortgageorigination.dao.CoverageDAO;
import com.jpmorgan.awm.pb.mortgageorigination.response.CoverageResponse;

@Service
public class CoverageDAOImpl implements CoverageDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	Logger logger = LoggerFactory.getLogger(CoverageDAOImpl.class);

	public CoverageResponse getClientOrAdvisorCoverage(String userType, String partyId) {
		CoverageResponse coverageResponse = new CoverageResponse();

		logger.info("getClientOrAdvisorCoverage :: Entry::  userType: {} partyId : {}", userType, partyId);

		String sql = "";

		boolean isValidClientOrAdvisor = false;
		try {
			if (userType.equalsIgnoreCase("C")) {
				sql = "select p.advisor_party_id as party_id from demo_client_team p where p.party_id = ?";
				isValidClientOrAdvisor = true;
			} else if (userType.equalsIgnoreCase("A")) {
				sql = "select p.party_id as party_id from demo_client_team p where p.ADVISOR_PARTY_ID = ?";
				isValidClientOrAdvisor = true;
			}

			if (isValidClientOrAdvisor) {
				List<Long> coverageList = jdbcTemplate.query(sql, new Object[] { partyId }, new UsersRowMapper());
				coverageResponse.setCoverageList(coverageList);

				logger.info("getClientOrAdvisorCoverage :: Exit ::  No of Parties : {}", coverageList.size());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return coverageResponse;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private class UsersRowMapper implements RowMapper<Long> {

		public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
			Long partyId = 0l;
			if (rs != null) {
				partyId = rs.getLong("party_id");
			}
			return partyId;
		}

	}

}
