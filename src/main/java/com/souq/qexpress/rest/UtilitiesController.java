package com.souq.qexpress.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.souq.qexpress.dao.impl.UtilityDaoImpl;

/**
 * Utilities controller includes any general purpose method.
 * 
 * @author Tariq
 *
 */
@Path("/utilities")
public class UtilitiesController {

	private Gson gson = new Gson();
	private UtilityDaoImpl utilImpl = new UtilityDaoImpl();

	/**
	 * Get number of records in existing table.
	 * 
	 * @param The
	 *            table name
	 * @return Response contains status and result
	 * @author Tariq
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getNumberOfRecords() {
		try {
			long numberOfRecordsInTable = utilImpl.getNumberOfRecords();
			String result = gson.toJson(numberOfRecordsInTable);

			return Response.status(Response.Status.OK).entity(result).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error !!").build();
		}
	}
}
