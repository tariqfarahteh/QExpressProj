package com.souq.qexpress.business;

import java.util.Date;

/**
 * This class represents a ShipmentOutput.
 *
 * @author Tariq
 */

public class ShipmentOutput {
	private long shipmentID;
	private String sellerName = "";
	private String buyerName = "";

	public ShipmentOutput() {

	}

	public ShipmentOutput(long shipmentID, String sellerName, String buyerName) {
		this.shipmentID = shipmentID;
		this.sellerName = sellerName;
		this.buyerName = buyerName;
	}

	public long getShipmentID() {
		return shipmentID;
	}

	public void setShipmentID(long shipmentID) {
		this.shipmentID = shipmentID;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getCurrentFacilityName() {
		return currentFacilityName;
	}

	public void setCurrentFacilityName(String currentFacilityName) {
		this.currentFacilityName = currentFacilityName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	private String currentFacilityName;
	private Date createDate;
}
