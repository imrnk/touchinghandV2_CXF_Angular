package com.touchinghand.endpoint.session;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

import org.apache.commons.lang3.StringUtils;
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
	
	
	private static final Logger LOGGER = Logger.getLogger(PsySessionResource.class.getName());
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Finds a Session by id", 
	notes = "Finds a Session by id", 
	response = PsySession.class)
	public Response findSessionById(@ApiParam @QueryParam("sessionId") String sessionId) {
		
		PsySession session = psySessionService.getSession(Integer.valueOf(sessionId));
		if (session == null) {
			ErrorResponse er = new ErrorResponse("422", "No session found with session id: " + sessionId);
			return Response.status(422).entity(er).build();
		}
		return Response.ok().entity(session).build();
	}
	
	
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
	
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find all sessions between start and end date", 
	notes = "Returns list of sessions between start and end date", 
	response = List.class)
	public Response getSessionBetween(@ApiParam @QueryParam("clientId") String clientId,
										@ApiParam @QueryParam("start-date") String startDate, 
										@ApiParam @QueryParam("end-date") String endDate) {
		
		List<PsySession> sessions  = null;
		if(StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
			ErrorResponse er = new ErrorResponse("422", "Dates are required to search");
			return Response.status(422).entity(er).build();
		}
			
		if(StringUtils.isEmpty(clientId)) {
			sessions = psySessionService.getSessionBetween(startDate, endDate);
		} else {
			sessions = psySessionService.getSessionOfClientBetween(Integer.valueOf(clientId), startDate, endDate);
		}
		
		if (sessions == null) {
			ErrorResponse er = new ErrorResponse("422", "No session found between " + startDate + " and " + endDate);
			return Response.status(422).entity(er).build();
		}
		return Response.ok().entity(sessions).build();
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a psy session for a client and return the created session Id", 
	notes = "Create a psy session for a client", 
	response = Integer.class)
	public Response createSession(PsySession session) {
		
		try {
			Integer sessionId = psySessionService.createSession(session);
			if(sessionId == null) {
				ErrorResponse er = new ErrorResponse("422", "Could not create Session");
				return Response.status(422).entity(er).build();
			}
			return Response.ok().entity(sessionId).build();
			
		} catch (RuntimeException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
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
			LOGGER.log(Level.SEVERE, e.getMessage());
			ErrorResponse er = new ErrorResponse("422", "Could not create Session");
			return Response.status(422).entity(er).build();
		}
	}
	
	@POST
	@Path("/treatment-data")
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
			LOGGER.log(Level.SEVERE, e.getMessage());
			ErrorResponse er = new ErrorResponse("422", "Could not create Treatment data");
			return Response.status(422).entity(er).build();
		}
	}
	
	@PUT
	@Path("/treatment-data/{treatmentId}")
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
			LOGGER.log(Level.SEVERE, e.getMessage());
			ErrorResponse er = new ErrorResponse("422", "Could not update Treatment data");
			return Response.status(422).entity(er).build();
		}
	}
	
	@GET
	@Path("/treatment-data/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieve list of Treatment data for a client", 
	notes = "Retrieve list of Treatment data for a client", 
	response = List.class)
	public Response getTreatmentData(@ApiParam @QueryParam("clientId") String clientId) {
		
		try {
			List<TreatmentData> treatmentData = psySessionService.getTreatmentData(Integer.valueOf(clientId));
			if(treatmentData == null) {
				ErrorResponse er = new ErrorResponse("422", "No Treatment data is found for clientId " + clientId);
				return Response.status(422).entity(er).build();
			}
			return Response.ok().entity(treatmentData).build();
			
		} catch (RuntimeException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			ErrorResponse er = new ErrorResponse("422", "Could not retrieve Treatment data");
			return Response.status(422).entity(er).build();
		}
	}
	
	@GET
	@Path("/treatment-data/session/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieve Treatment data for a session", 
	notes = "Retrieve Treatment data for a session", 
	response = TreatmentData.class)
	public Response getTreatmentDataForSession(@ApiParam @QueryParam("sessionId") String sessionId) {
		
		try {
			TreatmentData treatmentData = psySessionService.getTreatmentDataForSession(Integer.valueOf(sessionId));
			if(treatmentData == null) {
				ErrorResponse er = new ErrorResponse("422", "No Treatment data is found for session " + sessionId);
				return Response.status(422).entity(er).build();
			}
			return Response.ok().entity(treatmentData).build();
			
		} catch (RuntimeException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			ErrorResponse er = new ErrorResponse("422", "Could not retrieve Treatment data");
			return Response.status(422).entity(er).build();
		}
	}
}
