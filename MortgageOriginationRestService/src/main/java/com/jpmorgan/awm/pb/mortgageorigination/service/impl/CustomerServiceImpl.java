package com.jpmorgan.awm.pb.mortgageorigination.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpmorgan.awm.pb.mortgageorigination.model.Customer;
import com.jpmorgan.awm.pb.mortgageorigination.service.CustomerService;

@Service("userService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	public Customer findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveUser(Customer customer) {
		// TODO Auto-generated method stub

	}

	public void updateUser(Customer customer) {
		// TODO Auto-generated method stub

	}

	public void deleteUserById(long id) {
		// TODO Auto-generated method stub

	}

	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteAllCustomers() {
		// TODO Auto-generated method stub

	}

	public boolean isUserExist(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

}
