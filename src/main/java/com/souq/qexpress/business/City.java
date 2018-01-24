package com.souq.qexpress.business;

/**
 * This class represents a City.
 *
 * @author Tariq
 */

public class City {
	private long id;
	private String name = "";

	public City() {

	}

	public City(long id, String name) {
		this.id = id;
		this.name = name;
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
}
