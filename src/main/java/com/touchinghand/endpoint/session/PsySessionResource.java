package com.touchinghand.endpoint.session;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.touchinghand.common.ErrorResponse;
import com.touchinghand.dto.PsySession;
import com.touchinghand.dto.TreatmentData;
import com.touchinghand.service.session.PsySessionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/psy-session")
@Api(value = "/psy-session", tags = "psy session")
public class PsySessionResource {
	
	@Autowired
	private PsySessionService psySessionService;
	
	@GET
	@Path("/{clientId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find all sessions of client", 
	notes = "Returns list of sessions of client", 
	response = List.class)
	public Response getSession(@ApiParam @PathParam("clientId") String clientId) {
		
		List<PsySession> sessions = psySessionService.getSessionOfClient(Integer.valueOf(clientId));
		if (sessions == null) {
			ErrorResponse er = new ErrorResponse("422", "No session found for client: " + clientId);
			return Response.status(422).entity(er).build();
		}
		return Response.ok().entity(sessions).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a psy session for a client", 
	notes = "Create a psy session for a client", 
	response = Boolean.class)
	public Response createSession(PsySession session) {
		
		try {
			boolean success = psySessionService.createSession(session);
			if(!success) {
				ErrorResponse er = new ErrorResponse("422", "Could not create Session");
				return Response.status(422).entity(er).build();
			}
			return Response.ok().entity(Boolean.TRUE).build();
			
		} catch (RuntimeException e) {
			ErrorResponse er = new ErrorResponse("422", "Could not create Session");
			return Response.status(422).entity(er).build();
		}
	}
	
	
	@PUT
	@Path("/{sessionId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update a psy session for a client", 
	notes = "Update a psy session for a client", 
	response = Boolean.class)
	public Response updateSession(PsySession session, @ApiParam @PathParam("sessionId") String sessionId) {
		
		try {
			boolean success = psySessionService.updateSession(session);
			if(!success) {
				ErrorResponse er = new ErrorResponse("422", "Could not create Session");
				return Response.status(422).entity(er).build();
			}
			return Response.ok().entity(Boolean.TRUE).build();
			
		} catch (RuntimeException e) {
			ErrorResponse er = new ErrorResponse("422", "Could not create Session");
			return Response.status(422).entity(er).build();
		}
	}
	
	@POST
	@Path("/{treatment-data}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a Treatment data for a client", 
	notes = "Create a Treatment data for a client", 
	response = Boolean.class)
	public Response createTreatmentData(TreatmentData t) {
		
		try {
			boolean success = psySessionService.createTreatmentData(t);
			if(!success) {
				ErrorResponse er = new ErrorResponse("422", "Could not create Treatment data");
				return Response.status(422).entity(er).build();
			}
			return Response.ok().entity(Boolean.TRUE).build();
			
		} catch (RuntimeException e) {
			ErrorResponse er = new ErrorResponse("422", "Could not create Treatment data");
			return Response.status(422).entity(er).build();
		}
	}
	
	@PUT
	@Path("/{treatment-data}/{treatmentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update a Treatment data for a client", 
	notes = "Update a Treatment data for a client", 
	response = Boolean.class)
	public Response updateTreatmentData(TreatmentData t, @ApiParam @PathParam("treatmentId") String treatmentId) {
		
		try {
			boolean success = psySessionService.updateTreatmentData(t);
			if(!success) {
				ErrorResponse er = new ErrorResponse("422", "Could not update Treatment data");
				return Response.status(422).entity(er).build();
			}
			return Response.ok().entity(Boolean.TRUE).build();
			
		} catch (RuntimeException e) {
			ErrorResponse er = new ErrorResponse("422", "Could not update Treatment data");
			return Response.status(422).entity(er).build();
		}
	}
	
	@GET
	@Path("/{treatment-data}/{clientId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieve Treatment data for a client", 
	notes = "Retrieve Treatment data for a client", 
	response = TreatmentData.class)
	public Response getTreatmentData(@ApiParam @QueryParam("clientId") String clientId) {
		
		try {
			TreatmentData treatmentData = psySessionService.getTreatmentData(Integer.valueOf(clientId));
			if(treatmentData == null) {
				ErrorResponse er = new ErrorResponse("422", "Could not retrieve Treatment data");
				return Response.status(422).entity(er).build();
			}
			return Response.ok().entity(treatmentData).build();
			
		} catch (RuntimeException e) {
			ErrorResponse er = new ErrorResponse("422", "Could not retrieve Treatment data");
			return Response.status(422).entity(er).build();
		}
	}
}
