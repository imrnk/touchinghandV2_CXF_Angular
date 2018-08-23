package com.touchinghand.common.exeptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.touchinghand.common.ErrorResponse;
import com.touchinghand.common.exception.AuthenticationException;

/**
 * Exception mapper for {@link AuthenticationException}s.
 *
 */
@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

    @Override
    public Response toResponse(AuthenticationException exception) {

        Status status = Status.FORBIDDEN;
        ErrorResponse errorResponse = new ErrorResponse(status.name(), exception.getMessage());
        return Response.status(status).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
        
    }
}