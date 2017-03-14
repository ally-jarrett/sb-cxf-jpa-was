package com.was.spring.cxf.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.was.spring.cxf.model.Customer;
import com.was.spring.cxf.repository.CustomerRepository;
import com.was.spring.cxf.service.CustomerService;

//@Path("/sayHello")
//@Api("/sayHello")
@Component
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	@Override
	public String sayHello() {
        return "Hello from the Customer Service!!!!!!";
    }

	@Override
	public List<Customer> getAllCustomers() {
	
		return (List<Customer>) repository.findAll();
	}

}
