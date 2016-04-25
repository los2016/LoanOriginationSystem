package com.jpmorgan.awm.pb.mortgageorigination.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.jpmorgan.awm.pb.mortgageorigination.dao.CoverageDAO;
import com.jpmorgan.awm.pb.mortgageorigination.response.CoverageResponse;

@Service
public class CoverageDAOImpl implements CoverageDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public CoverageResponse getClientOrAdvisorCoverage(String userType, String partyId) {
		CoverageResponse coverageResponse = new CoverageResponse();

		// coverageList.add(new Long(2341434l));
		// coverageList.add(new Long(5678568l));
		// coverageList.add(new Long(78782334l));
		// coverageList.add(new Long(99234134l));
		// coverageResponse.setCoverageList(coverageList);

		String sql = "";
		if (userType.equalsIgnoreCase("C")) {
			sql = "select p.advisor_party_id as party_id from demo_client_team p where p.party_id = ?";
		} else {
			sql = "select p.party_id as party_id from demo_client_team p where p.ADVISOR_PARTY_ID = ?";
		}

		List<Long> coverageList = jdbcTemplate.query(sql,new Object[] { partyId },  new UsersRowMapper());
		coverageResponse.setCoverageList(coverageList);

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
