package com.was.spring.cxf.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.was.spring.cxf.impl.CustomerServiceImpl;
import com.was.spring.cxf.impl.HelloServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@ImportResource({"classpath:META-INF/cxf/cxf.xml"})
@EnableConfigurationProperties
public class CxfConfiguration {

	private static final Log logger = LogFactory.getLog(CxfConfiguration.class);

	@Autowired
	private ApplicationContext ctx;

	@Value("${cxf.path:/services/*}")
	private String cxfPath;

	@Bean
	public ServletRegistrationBean cxfServletRegistrationBean() {
		return new ServletRegistrationBean(new CXFServlet(), cxfPath);
	}
	
//	@Bean
//	public ServletRegistrationBean h2servletRegistration() {
//	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
//	    registration.addUrlMappings("/h2-console/*");
//	    registration.addInitParameter("webAllowOthers", "true");
//	    return registration;
//	}

	@Bean
	public Server jaxRsServer() {
		// List of Services
		List<Object> services = new ArrayList<Object>();
		services.add(new HelloServiceImpl());
		services.add(new CustomerServiceImpl());
		services.add(new WebServlet());
		
		JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
		factory.setBus(ctx.getBean(SpringBus.class));
		factory.setAddress("/");
		factory.setProvider(new JacksonJaxbJsonProvider());
		factory.setServiceBeans(Arrays.<Object> asList(services));
		Server server = factory.create();
		return server;
	}

//	@Bean
//	@ConditionalOnMissingBean
//	public JacksonJsonProvider jsonProvider(ObjectMapper objectMapper) {
//		JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
//		provider.setMapper(objectMapper);
//		return provider;
//	}
//
//	@Bean
//	@ConditionalOnMissingBean
//	public ObjectMapper objectMapper() {
//		return new ObjectMapper();
//	}

}
