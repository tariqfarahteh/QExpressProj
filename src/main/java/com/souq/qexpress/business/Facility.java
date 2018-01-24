package com.souq.qexpress.business;

/**
 * This class represents a Facility.
 *
 * @author Tariq
 */
public class Facility {
	private long id;
	private String name = "";
	private int cityID;
	private City city = new City();;

	public Facility() {

	}

	public Facility(long id, String name, City city) {
		this.id = id;
		this.name = name;
		this.city = city;
	}

	public Facility(long id, String name, int cityID) {
		this.id = id;
		this.name = name;
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
