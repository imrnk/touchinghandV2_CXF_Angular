package com.touchinghand.endpoint.client;

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
import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.touchinghand.common.ErrorResponse;
import com.touchinghand.dto.Client;
import com.touchinghand.dto.ClientMse;
import com.touchinghand.service.client.PsyClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/clients")
@Api(value = "/clients", tags = "clients")
public class ClientResource {

	@Autowired
	private PsyClientService clientService;
	private static final Logger LOGGER = Logger.getLogger(ClientResource.class.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find all active Clients", 
	notes = "Returns list of active Clients", 
	response = List.class)
	public Response allActiveClients() {
		List<Client> activeClients = clientService.findActiveClients();
		if (CollectionUtils.isEmpty(activeClients)) {
			ErrorResponse er = new ErrorResponse("422", "No active clients found");
			return Response.status(422).entity(er).build();
		}
		return Response.ok().entity(activeClients).build();

	}
	
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find a Client searched by name or id", 
	notes = "Returns a Client searched by name or id", 
	response = Client.class)
	public Response clientByNameOrId(@ApiParam @QueryParam("fname") String fname, 
								 @ApiParam @QueryParam("lname") String lname,
								@ApiParam @QueryParam("id") String id, 
								@ApiParam(required=true) @QueryParam("search-by") String searchBy) {
		
		if(searchBy.equalsIgnoreCase("id")) {
			return clientById(id);
		}
		
		if(StringUtils.isEmpty(fname) && StringUtils.isEmpty(lname)) {
			ErrorResponse er = new ErrorResponse("422", "Either first name or last name is required to search");
			return Response.status(422).entity(er).build();
		}
		
		Client result = clientService.findClientByName(fname, lname);
		if (result == null) {
			ErrorResponse er = new ErrorResponse("422", "No client found by name: " + fname + " " + lname);
			return Response.status(422).entity(er).build();
		}
		return Response.ok().entity(result).build();

	}
	
	
	private Response clientById(String id) {

		if(StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
			ErrorResponse er = new ErrorResponse("422", "Correct clientId is required to search");
			return Response.status(422).entity(er).build();
		}
		
		Client result = clientService.findClientById(Integer.valueOf(id));
		if (result == null) {
			ErrorResponse er = new ErrorResponse("422", "No client found by id: " + id);
			return Response.status(422).entity(er).build();
		}
		return Response.ok().entity(result).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add a Client", 
			notes = "Add a Client", 
			response = Boolean.class)
	public Response addClient(Client c) {

		try {
			boolean success = clientService.addClient(c);
			if (!success) {
				ErrorResponse er = new ErrorResponse("422", "Could not create Client Information");
				return Response.status(422).entity(er).build();
			}
			return Response.ok().entity(Boolean.TRUE).build();
		} catch (RuntimeException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			ErrorResponse er = new ErrorResponse("422", "Could not create Client Information");
			return Response.status(422).entity(er).build();
		}
	}
	
	@PUT
	@Path("/{clientId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update a Client", 
			notes = "Update a Client", 
			response = Boolean.class)
	public Response updateClient(Client c, @ApiParam(required=true) @PathParam("clientId") String clientId) {
		
		if(StringUtils.isEmpty(clientId) || !StringUtils.isNumeric(clientId)) {
			ErrorResponse er = new ErrorResponse("422", "Correct ClienId is required to update client");
			return Response.status(422).entity(er).build();
		}
		try {
			boolean success = clientService.updateClient(Integer.valueOf(clientId), c);
			if (!success) {
				ErrorResponse er = new ErrorResponse("422", "Could not update Client of id: " + clientId);
				return Response.status(422).entity(er).build();
			}
			return Response.ok().entity(Boolean.TRUE).build();
		} catch (RuntimeException e) {
			if(e.getMessage() != null) {
				ErrorResponse er = new ErrorResponse("422", e.getMessage());
				return Response.status(422).entity(er).build();
			}
			LOGGER.log(Level.SEVERE, e.getMessage());
			ErrorResponse er = new ErrorResponse("422", "Could not update Client of id: " + clientId);
			return Response.status(422).entity(er).build();
		}
	}
	
	@POST
	@Path("/mse/{clientId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add a client MSE", 
			notes = "Add a client MSE", 
			response = Boolean.class)
	public Response addClientMse(ClientMse cm, @ApiParam @PathParam("clientId") String clientId) {

		if(StringUtils.isEmpty(clientId) || !StringUtils.isNumeric(clientId)) {
			ErrorResponse er = new ErrorResponse("422", "Correct ClientId is required to create Client MSE");
			return Response.status(422).entity(er).build();
		}
		
		try {
			boolean success = clientService.addClientMse(Integer.valueOf(clientId), cm);
			if (!success) {
				ErrorResponse er = new ErrorResponse("422", "Could not create Client MSE Information");
				return Response.status(422).entity(er).build();
			}
			return Response.ok().entity(Boolean.TRUE).build();
		} catch (RuntimeException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			ErrorResponse er = new ErrorResponse("422", "Could not create Client MSE Information");
			return Response.status(422).entity(er).build();
		}
	}
	
	@PUT
	@Path("/mse/{clientMseId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update a client MSE", 
			notes = "Update a client MSE", 
			response = Boolean.class)
	public Response updateClientMse(ClientMse cm, @ApiParam @PathParam("clientMseId") String clientMseId) {

		if(StringUtils.isEmpty(clientMseId) || !StringUtils.isNumeric(clientMseId)) {
			ErrorResponse er = new ErrorResponse("422", "Correct clientMseId is required to update Client MSE");
			return Response.status(422).entity(er).build();
		}
		
		try {
			boolean success = clientService.updateClientMse(Integer.valueOf(clientMseId), cm);
			if (!success) {
				ErrorResponse er = new ErrorResponse("422", "Could not Update Client MSE Information");
				return Response.status(422).entity(er).build();
			}
			return Response.ok().entity(Boolean.TRUE).build();
		} catch (RuntimeException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			ErrorResponse er = new ErrorResponse("422", "Could not create Client MSE Information");
			return Response.status(422).entity(er).build();
		}
	}
	
	@GET
	@Path("/mse/{clientId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find a client MSE searched by client-id", 
	notes = "Returns a Client MSE searched by client-id", 
	response = ClientMse.class)
	public Response clientMseById(@ApiParam @PathParam("clientId") String clientId) {
		
		if(StringUtils.isEmpty(clientId) || !StringUtils.isNumeric(clientId)) {
			ErrorResponse er = new ErrorResponse("422", "Correct clientId is required to search Client MSE");
			return Response.status(422).entity(er).build();
		}
		
		ClientMse result = clientService.getClientMse(Integer.valueOf(clientId));
		if (result == null) {
			ErrorResponse er = new ErrorResponse("422", "No client MSE found by id: " + clientId);
			return Response.status(422).entity(er).build();
		}
		return Response.ok().entity(result).build();

	}
	

}
