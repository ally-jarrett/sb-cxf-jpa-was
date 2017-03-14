package com.was.spring.cxf.utils;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailUtils {

	private Logger logger = LoggerFactory.getLogger(MailUtils.class);

	/**
	 * Returns a javax.ws.rs.core.Response object for a given responseEntity & HTTP Status Code. 
	 * @param responseEntity
	 * @param status
	 * @return
	 */
	public Response buildResponse(Object responseEntity, Status status) {
		logger.debug("Building new Repsonse Entity with Status {} ", status.getStatusCode());
		return Response.status(status).entity(responseEntity).build();
	}

}
