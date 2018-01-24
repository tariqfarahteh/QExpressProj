package com.souq.qexpress.dao;

import com.souq.qexpress.business.Facility;

import java.util.List;

public interface FacilityDao {

	Facility getFacility(long facilityID);

	List<Facility> getFacilities();
}
