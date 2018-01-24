package com.souq.qexpress.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.souq.qexpress.dao.ConnectionManager;
import com.souq.qexpress.business.Facility;
import com.souq.qexpress.dao.FacilityDao;

public class FacilityDaoImpl implements FacilityDao {

	private static final String FACILITY_CITY_ID = "CityID";
	private static final String FACILITY_NAME = "Name";
	private static final String FACILITY_ID = "id";

	/**
	 * Get Facility
	 * 
	 * @return List of Facility object
	 * @author Tariq
	 */
	@Override
	public Facility getFacility(long facilityID) {

		Facility facility = null;
		try (Connection connection = ConnectionManager.getInstance().getConnection();
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Facility WHERE ID = ?");) {

			stmt.setLong(1, facilityID);

			try (ResultSet rs = stmt.executeQuery()) {

				if (rs.next()) {
					facility = extractFacilityFromResultSet(rs);
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return facility;

	}

	/**
	 * Get All Facilities
	 * 
	 * @return List of Facility object
	 * @author Tariq
	 */
	@Override
	public List<Facility> getFacilities() {

		List<Facility> facilities = null;
		try (Connection connection = ConnectionManager.getInstance().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Facility");) {

			facilities = new ArrayList<Facility>();
			while (rs.next()) {
				facilities.add(extractFacilityFromResultSet(rs));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return facilities;

	}

	/**
	 * Extract Facility object From ResultSet
	 * 
	 * @param ResultSet
	 * @return Facility object
	 * @author Tariq
	 */
	private Facility extractFacilityFromResultSet(ResultSet rs) throws SQLException {
		Facility facility = new Facility();
		facility.setID(rs.getLong(FACILITY_ID));
		facility.setName(rs.getString(FACILITY_NAME));
		facility.setCityID(rs.getInt(FACILITY_CITY_ID));
		return facility;
	}

}
