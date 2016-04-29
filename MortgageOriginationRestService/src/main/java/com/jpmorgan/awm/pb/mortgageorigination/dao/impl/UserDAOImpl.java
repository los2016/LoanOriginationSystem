package com.jpmorgan.awm.pb.mortgageorigination.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.jpmorgan.awm.pb.mortgageorigination.dao.UserDAO;
import com.jpmorgan.awm.pb.mortgageorigination.response.UserDetailsResponse;
import com.myorg.losmodel.model.LOSResponse;
import com.myorg.losmodel.model.User;
import com.myorg.losmodel.model.UserInfo;

@Service
public class UserDAOImpl implements UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	public boolean addUser(UserInfo userInfo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public User getUserDetails(String userCd) throws Exception {
		// String partyId = "";
		String sql = "select * from users where user_cd = ?";
		User user = new User();

		try {
			user = jdbcTemplate.queryForObject(sql, new Object[] { userCd }, new UsersRowMapper());

		} catch (Exception e) {
			throw new Exception("PARTY ID NOT FOUND for user " + userCd, e);
		}
		return user;

	}

	public UserDetailsResponse authenticateUser(String userId, String password) {
		UserDetailsResponse userDetailsResponse = new UserDetailsResponse();

		logger.info("authenticateUser :: Entry::  userId: {} ", userId);

		String sql = "select * from USERS where USER_CD = ?";
		User user = new User();
		try {
			user = jdbcTemplate.queryForObject(sql, new Object[] { userId }, new UsersRowMapper());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOSResponse messageResponse = new LOSResponse();
			messageResponse.setReturnMsg("Login Failed");
			messageResponse.setReturnType("Error");
			userDetailsResponse.setResponse(messageResponse);
			return userDetailsResponse;
		}

		if (user != null && user.getPassword().equalsIgnoreCase(password)) {
			logger.info("authenticateUser :: Authenticated Succesfuly ");

			LOSResponse messageResponse = new LOSResponse();

			messageResponse.setReturnMsg("User logged in Sucessfully");
			messageResponse.setReturnType("Success");
			userDetailsResponse.setResponse(messageResponse);

			String sqlUpdate = "update USERS set LAST_LOGIN_TS = ? where USER_CD = ?";
			try {
				jdbcTemplate.update(sqlUpdate, new Date(), userId);
			} catch (DataAccessException e) {
				logger.error("authenticateUser :: While updating time stamp ");
				e.printStackTrace();
			}

			if (user.getRoleId() == 2000) {
				user.setUserType("A");
			} else if (user.getRoleId() == 1000) {
				user.setUserType("C");
			}
			// Removes Role ID as we are sending UserType
			user.setRoleId(0);
			user.setPassword("");
			userDetailsResponse.setUser(user);
		} else {
			LOSResponse messageResponse = new LOSResponse();
			messageResponse.setReturnMsg("Login Failed");
			messageResponse.setReturnType("Error");
			userDetailsResponse.setResponse(messageResponse);
		}
		logger.info("authenticateUser :: Exit ");

		return userDetailsResponse;
	}

	public List<User> getAllUsers() {
		String sql = "select * from USERS where USER_CD";
		List<User> userList = jdbcTemplate.query(sql, new UsersRowMapper());
		return userList;
	}

	private class UsersRowMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User userInfo = new User();
			if (rs != null) {
				userInfo.setUserId(rs.getString("USER_CD"));
				userInfo.setUserName(rs.getString("USER_FIRST_NM") + " " + rs.getString("USER_LAST_NM"));
				userInfo.setPartyId(rs.getInt("PARTY_ID"));
				userInfo.setPassword(rs.getString("USER_PASSWORD_CD"));
				userInfo.setRoleId(rs.getInt("ROLE_ID"));
				userInfo.setLastLoginTimestamp(rs.getTimestamp("LAST_LOGIN_TS"));
			}

			return userInfo;
		}

	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
