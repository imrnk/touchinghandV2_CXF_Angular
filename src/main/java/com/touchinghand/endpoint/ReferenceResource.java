package com.touchinghand.endpoint;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.touchinghand.common.ErrorResponse;
import com.touchinghand.dto.ReferenceData;
import com.touchinghand.service.ReferenceDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/ref-data")
@Api(value = "/ref-data", tags = "reference data")
public class ReferenceResource {
	
	@Autowired
	private ReferenceDataService rdService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns list of reference data of passed type id", 
	notes = "Returns list of reference data of passed type id", 
	response = List.class)
	public Response getReferenceDataOfType(@ApiParam @QueryParam("typeId") int typeId) {
		List<ReferenceData> refData = rdService.getReferenceDataOfType(typeId);
		if(refData == null) {
			ErrorResponse er = new ErrorResponse("422", "No reference data found");
			return Response.status(422).entity(er).build();
		}
		return Response.ok().entity(refData).build();
	}

}
