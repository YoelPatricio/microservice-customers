package com.patricio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patricio.model.Customer;

@Repository
public interface ICustomerDAO extends JpaRepository<Customer, Long>{

}
