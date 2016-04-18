package com.jpmorgan.awm.pb.mortgageorigination.service;

import java.util.List;

import com.jpmorgan.awm.pb.mortgageorigination.model.Customer;

public interface CustomerService {

	Customer findById(long id);

	Customer findByName(String name);

	void saveUser(Customer customer);

	void updateUser(Customer customer);

	void deleteUserById(long id);

	List<Customer> findAllCustomers();

	void deleteAllCustomers();

	public boolean isUserExist(Customer customer);

}
