package com.jpmorgan.awm.pb.mortgageorigination.dao;

import java.sql.SQLException;
import java.util.List;

import com.jpmorgan.awm.pb.mortgageorigination.response.UserDetailsResponse;
import com.myorg.losmodel.model.User;
import com.myorg.losmodel.model.UserInfo;

public interface UserDAO {

	public boolean addUser(UserInfo userInfo) throws SQLException;

	public UserDetailsResponse authenticateUser(String userId, String password);

	public List<User> getAllUsers();
	
	public User getUserDetails(String userCd) throws Exception;
	
}
