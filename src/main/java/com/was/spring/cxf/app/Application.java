package com.was.spring.cxf.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.was.spring.cxf.model.Customer;
import com.was.spring.cxf.repository.CustomerRepository;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	private Logger logger = LoggerFactory.getLogger(Application.class);

//	@Autowired
//	private CustomerRepository repository;
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
//    
//	@Bean
//	public CommandLineRunner demo() {
//		CommandLineRunner commandLineRunner = new CommandLineRunner() {
//
//			@Override
//			public void run(String... arg0) throws Exception {
//				// save a couple of customers
//				repository.save(new Customer("Jack", "Bauer"));
//				repository.save(new Customer("Chloe", "O'Brian"));
//				repository.save(new Customer("Kim", "Bauer"));
//				repository.save(new Customer("David", "Palmer"));
//				repository.save(new Customer("Michelle", "Dessler"));
//
//				// fetch all customers
//				logger.info("Customers found with findAll():");
//				logger.info("-------------------------------");
//				for (Customer customer : repository.findAll()) {
//					logger.info(customer.toString());
//				}
//				logger.info("");
//
//				// fetch an individual customer by ID
//				Customer customer = repository.findOne(1L);
//				logger.info("Customer found with findOne(1L):");
//				logger.info("--------------------------------");
//				logger.info(customer.toString());
//				logger.info("");
//
//				// fetch customers by last name
//				logger.info("Customer found with findByLastName('Bauer'):");
//				logger.info("--------------------------------------------");
//				for (Customer bauer : repository.findByLastName("Bauer")) {
//					logger.info(bauer.toString());
//				}
//				logger.info("");
//			}
//		};
//		return commandLineRunner;
//	}

}
