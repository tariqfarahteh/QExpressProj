package com.souq.qexpress.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.souq.qexpress.dao.ConnectionManager;
import com.souq.qexpress.dao.UtilityDao;

public class UtilityDaoImpl implements UtilityDao {

	private static final String UTILITY_NUMBER_OF_RECORDS = "numberOfRecords";

	/**
	 * Get number of records from the shipment table
	 * 
	 * @return number of records
	 * @author Tariq
	 */
	@Override
	public long getNumberOfRecords() {

		long numberOfRecords = 0;
		try (Connection connection = ConnectionManager.getInstance().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select count(*) As numberOfRecords from shipmentH")) {

			if (rs.next()) {
				numberOfRecords = rs.getLong(UTILITY_NUMBER_OF_RECORDS);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numberOfRecords;

	}

}
