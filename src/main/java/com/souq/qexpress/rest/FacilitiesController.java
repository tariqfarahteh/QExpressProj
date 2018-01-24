package com.souq.qexpress.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.souq.qexpress.business.Facility;
import com.souq.qexpress.dao.impl.FacilityDaoImpl;

/**
 * 
 * This controller is used for the system facilities operations.
 * 
 * @author Tariq
 */
@Path("/facilities")
public class FacilitiesController {

	private Gson gson = new Gson();
	private FacilityDaoImpl facilityImpl = new FacilityDaoImpl();

	/**
	 * Get all existing facilities.
	 * 
	 * @return Response contains status and result
	 * @author Tariq
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllFacilities() {

		try {
			List<Facility> facilitiesList = facilityImpl.getFacilities();
			String result = gson.toJson(facilitiesList);

			return Response.status(Response.Status.OK).entity(result).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error !!").build();
		}
	}

}
