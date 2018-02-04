package com.touchinghand.endpoint.client;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.common.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.touchinghand.common.ErrorResponse;
import com.touchinghand.dto.Client;
import com.touchinghand.service.client.PsyClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/clients")
@Api(value = "/clients", tags = "clients")
public class ClientResource {

	@Autowired
	private PsyClientService clientService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find active clients", 
	notes = "Returns list of active clients", 
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
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find a client searched by name", 
	notes = "Returns a Client searched by name", 
	response = Client.class)
	public Response clientByName(@ApiParam @QueryParam("name") String name) {
		Client result = clientService.findClientByName(name);
		if (result == null) {
			ErrorResponse er = new ErrorResponse("422", "No client found by name: " + name);
			return Response.status(422).entity(er).build();
		}
		return Response.ok().entity(result).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add a client", 
			notes = "Add a client", 
			response = Boolean.class)
	public Response addClient(Client c) {

		try {
			boolean success = clientService.addClient(c);
			if (!success) {
				ErrorResponse er = new ErrorResponse("422", "Could not create Client Information");
				return Response.status(422).entity(er).build();
			}
			return Response.ok().build();
		} catch (RuntimeException e) {
			ErrorResponse er = new ErrorResponse("422", "Could not create Client Information");
			return Response.status(422).entity(er).build();
		}
	}

}
