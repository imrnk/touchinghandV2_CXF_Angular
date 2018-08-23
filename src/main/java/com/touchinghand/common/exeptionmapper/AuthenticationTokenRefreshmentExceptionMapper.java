package com.touchinghand.common.exeptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.touchinghand.common.ErrorResponse;
import com.touchinghand.common.exception.AuthenticationTokenRefreshmentException;

/**
 * Exception mapper for {@link AuthenticationTokenRefreshmentException}s.
 *
 */
@Provider
public class AuthenticationTokenRefreshmentExceptionMapper implements ExceptionMapper<AuthenticationTokenRefreshmentException> {


    @Override
    public Response toResponse(AuthenticationTokenRefreshmentException exception) {


        Status status = Status.FORBIDDEN;
        ErrorResponse errorResponse = new ErrorResponse(status.name(), "The authentication token cannot be refreshed.");
        return Response.status(status).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
    }
}