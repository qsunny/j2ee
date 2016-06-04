package com.aaron.winkwebapp.api.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper for mapping a UserException that was thrown from an
 * application into a human readable message
 */
@Provider
public class UserExceptonMapper implements ExceptionMapper<UserException> {

	@Override
	public Response toResponse(UserException e) {
		return Response.status(404).entity(e.getUser() +  " does not exist").build();
	}

}
