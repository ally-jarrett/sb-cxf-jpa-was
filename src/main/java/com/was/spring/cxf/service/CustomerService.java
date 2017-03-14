package com.was.spring.cxf.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.was.spring.cxf.model.Customer;

@Path("/customer")
@Service
public interface CustomerService {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    List<Customer> getAllCustomers();
    
    @GET
    @Path("/sayHello")
    @Produces(MediaType.TEXT_PLAIN)
    String sayHello();

}