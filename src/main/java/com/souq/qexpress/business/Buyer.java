package com.souq.qexpress.business;

/**
 * This class represents a Buyer.
 *
 * @author Tariq
 */
public class Buyer {
	private long id;
	private String name = "";
	private String phoneNumber = "";
	private int cityID;
	private City city = new City();

	public Buyer() {

	}

	public Buyer(long id, String name, String phoneNumber, City city) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.city = city;
	}

	public Buyer(long id, String name, String phoneNumber, int cityID) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.cityID = 0;
	}

	public void setID(long id) {
		this.id = id;
	}

	public long getID() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setCityID(int cityID) {
		this.cityID = cityID;
	}

	public int getCityID() {
		return this.cityID;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public City getCity() {
		return this.city;
	}
}
