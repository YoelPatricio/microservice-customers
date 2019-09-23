package com.patricio.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patricio.dao.ICustomerDAO;
import com.patricio.model.Customer;
import com.patricio.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private ICustomerDAO customerDAO;

	@Override
	public Customer registrar(Customer customer) {
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaNac = LocalDate.parse(customer.getDateOfBirth().toString(), fmt);
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fechaNac, ahora);
		
		customer.setAge(periodo.getYears());
		
		return customerDAO.save(customer);
	}

	@Override
	public List<Customer> listar() {
		return customerDAO.findAll();
	}

	@Override
	public Integer getAverageAge() {
		List<Customer> customers = customerDAO.findAll();
		
		Integer prom = 0;
		for (Customer customer : customers) {
			prom += customer.getAge();
		}

	    return prom / customers.size();  
	}

	@Override
	public Double getStandardDeviation() {
		List<Customer> customers = customerDAO.findAll();
		double prom, sum = 0; int i, n = customers.size();
	    prom = getAverageAge();

	    for (Customer customer : customers) {
			sum += Math.pow ( customer.getAge() - prom, 2 );
		}

	    return Math.sqrt ( sum / ( double ) n );
	}
	
}
