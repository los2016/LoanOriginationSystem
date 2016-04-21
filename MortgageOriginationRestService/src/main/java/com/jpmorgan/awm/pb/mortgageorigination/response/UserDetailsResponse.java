package com.jpmorgan.awm.pb.mortgageorigination.response;

import com.myorg.losmodel.model.LOSResponse;
import com.myorg.losmodel.model.User;

public class UserDetailsResponse {

	private User user;
	private LOSResponse response;

	public LOSResponse getResponse() {
		return response;
	}

	public void setResponse(LOSResponse response) {
		this.response = response;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
