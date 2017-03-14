package com.was.spring.cxf.impl;

//import io.swagger.annotations.Api;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.was.spring.cxf.model.Customer;
import com.was.spring.cxf.repository.CustomerRepository;
import com.was.spring.cxf.service.HelloService;

//@Api("/sayHello")
@Component
public class HelloServiceImpl implements HelloService {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
	
	@Autowired
	private CustomerRepository repository;
	
	public String sayHello(String a) {
        return "Hello " + a + ", Welcome to CXF RS Spring Boot World!!!";
    }
	
	@Override
	public String getAllCustomers() {
		logger.info("INVOKING.....");
		
		if (repository == null){
			logger.info("REPO IS NULL....");
		}
		
		if (repository.count() < 1){
			logger.info("Saving Contacts....");
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));
		}
		
		for (Customer customer : repository.findAll()) {
			logger.info(customer.toString());
		}
		
		return "Hello World";
	}

}
