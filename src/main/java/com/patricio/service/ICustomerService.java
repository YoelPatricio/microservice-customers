package com.patricio.service;

import java.util.List;

import com.patricio.model.Customer;

public interface ICustomerService{
	
	Customer registrar(Customer customer);

	List<Customer> listar();
	
	Integer getAverageAge();
	
	Double getStandardDeviation();
	
}
