package com.souq.qexpress.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import com.souq.qexpress.business.Shipment;
import com.souq.qexpress.business.ShipmentOutput;
import com.souq.qexpress.dao.impl.ShipmentDaoImpl;
import com.souq.qexpress.util.Utilities;

/**
 * 
 * This controller is used for the system shipments operations.
 * 
 * @author Tariq
 */
@Path("/shipments")
public class ShipmentsController {

	private Gson gson = new Gson();
	private ShipmentDaoImpl shipImpl = new ShipmentDaoImpl();

	/**
	 * Add new shipment.
	 * 
	 * @param json
	 *            object contains shipment parameters
	 * @return Response contains status and result
	 * @author Tariq
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addShipment(String shipmentJson) {

		long shipmentID = Utilities.generateShipmentID();

		Shipment shipment = gson.fromJson(shipmentJson, Shipment.class);

		if (shipment.getBuyerID() == 0 || shipment.getSellerID() == 0 || shipment.getFromFacilityID() == 0
				|| shipment.getToFacilityID() == 0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Error : Missing Parameter").build();
		}
		shipment.setID(shipmentID);
		if (shipImpl.insertShipment(shipment)) {
			return Response.status(Response.Status.CREATED).entity(gson.toJson(shipment)).build();
		}

		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error !!").build();

	}

	/**
	 * Get all existing shipments filtered by facility number.
	 * 
	 * @param facilityID
	 * @param pageNumber
	 * @return Response contains status and result
	 * @author Tariq
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllShipments(@QueryParam("facilityId") long facilityId,
			@QueryParam("pageNumber") long pageNumber) {

		try {
			long pageIndex = (pageNumber - 1) * 20;
			if (pageNumber == 0) {
				pageIndex = 0;
			}
			if (facilityId == 0) {
				facilityId = -1;
			}

			List<ShipmentOutput> shipmentsList = shipImpl.getAllShipments(facilityId, pageIndex);
			String result = gson.toJson(shipmentsList);
			return Response.status(Response.Status.OK).entity(result).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal Server Error !!").build();
		}
	}

}
