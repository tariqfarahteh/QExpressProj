package com.souq.qexpress.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.souq.qexpress.business.Buyer;
import com.souq.qexpress.dao.impl.BuyerDaoImpl;

/**
 * 
 * This controller is used for the system buyers operations.
 * 
 * @author Tariq
 */
@Path("/buyers")
public class BuyerController {

	private Gson gson = new Gson();
	private BuyerDaoImpl buyerImpl = new BuyerDaoImpl();

	/**
	 * Get all existing buyers.
	 * 
	 * @return Response contains status and result
	 * @author Tariq
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllBuyers() {
		try {
			List<Buyer> buyerList = buyerImpl.getAllBuyers();
			String result = gson.toJson(buyerList);

			return Response.status(Response.Status.OK).entity(result).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error !!").build();
		}
	}
}
