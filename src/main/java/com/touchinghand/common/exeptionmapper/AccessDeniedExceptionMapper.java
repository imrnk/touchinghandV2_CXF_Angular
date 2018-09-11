package com.touchinghand.common.exeptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.touchinghand.common.ErrorResponse;
import com.touchinghand.common.exception.AccessDeniedException;

/**
 * Exception mapper for {@link AccessDeniedException}s.
 *
 */
@Provider
public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {


    @Override
    public Response toResponse(AccessDeniedException exception) {

        Status status = Status.FORBIDDEN;
        ErrorResponse errorResponse = new ErrorResponse(status.name(), exception.getMessage());
        return Response.status(status).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
    }
}