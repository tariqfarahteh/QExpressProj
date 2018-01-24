package com.souq.qexpress.business;

/**
 * This class represents an Item.
 *
 * @author Tariq
 */

public class Item {
	private long id;
	private String description;
	private long sellerID;
	private Seller seller = new Seller();

	public Item() {

	}

	public Item(long id, String description, long sellerID) {
		this.id = id;
		this.description = description;
		this.sellerID = sellerID;
	}

	public Item(long id, String description, Seller seller) {
		this.id = id;
		this.description = description;
		this.seller = seller;
	}

	public void setID(long id) {
		this.id = id;
	}

	public long getID() {
		return this.id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setSellerID(long sellerID) {
		this.sellerID = sellerID;
	}

	public long getSellerID() {
		return this.sellerID;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Seller getSeller() {
		return this.seller;
	}
}
