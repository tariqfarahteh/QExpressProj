package com.souq.qexpress.business;

/**
 * This class represents a Shipment.
 *
 * @author Tariq
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Shipment {
	private long id;
	private long sellerID;
	private Seller seller = new Seller();
	private long buyerID;
	private Buyer buyer = new Buyer();
	private long fromFacilityID;
	private Facility fromFacility = new Facility();;
	private long toFacilityID;
	private Facility toFacility = new Facility();
	private Date createDate = new Date();
	private List<Item> items = new ArrayList<Item>();

	public Shipment() {

	}

	public Shipment(long id, long sellerID, long buyerID, long fromFacilityID, long toFacilityID, Date createDate,
			List<Item> items) {
		this.id = id;
		this.sellerID = sellerID;
		this.buyerID = buyerID;
		this.fromFacilityID = fromFacilityID;
		this.toFacilityID = toFacilityID;
		this.createDate = createDate;
		this.items = items;
	}

	public Shipment(long id, Seller seller, Buyer buyer, Facility fromFacility, Facility toFacility, Date createDate,
			List<Item> items) {
		this.id = 0;
		this.seller = new Seller();
		this.buyer = new Buyer();
		this.fromFacility = new Facility();
		this.toFacility = new Facility();
		this.createDate = new Date();
		this.items = items;
	}

	public long getID() {
		return id;
	}

	public void setID(long id) {
		this.id = id;
	}

	public long getSellerID() {
		return sellerID;
	}

	public void setSellerID(long sellerID) {
		this.sellerID = sellerID;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public long getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(long buyerID) {
		this.buyerID = buyerID;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public long getFromFacilityID() {
		return fromFacilityID;
	}

	public void setFromFacilityID(long fromFacilityID) {
		this.fromFacilityID = fromFacilityID;
	}

	public Facility getFromFacility() {
		return fromFacility;
	}

	public void setFromFacility(Facility fromFacility) {
		this.fromFacility = fromFacility;
	}

	public long getToFacilityID() {
		return toFacilityID;
	}

	public void setToFacilityID(long toFacilityID) {
		this.toFacilityID = toFacilityID;
	}

	public Facility getToFacility() {
		return toFacility;
	}

	public void setToFacility(Facility toFacility) {
		this.toFacility = toFacility;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
