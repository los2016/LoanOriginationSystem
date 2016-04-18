package com.jpmorgan.awm.pb.mortgageorigination.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jpmorgan.awm.pb.mortgageorigination.model.Customer;
import com.jpmorgan.awm.pb.mortgageorigination.service.CustomerService;

@RestController
public class MortgageRestController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/customer/", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> listAllCustomer() {
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer(123, "Gagan", new Date(), 45));
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);

	}
}
