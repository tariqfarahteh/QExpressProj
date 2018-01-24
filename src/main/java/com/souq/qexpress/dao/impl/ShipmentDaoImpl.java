package com.souq.qexpress.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.souq.qexpress.dao.ConnectionManager;
import com.souq.qexpress.business.Item;
import com.souq.qexpress.business.Shipment;
import com.souq.qexpress.business.ShipmentOutput;
import com.souq.qexpress.dao.ShipmentDao;

public class ShipmentDaoImpl implements ShipmentDao {

	private static final String SHIPMENT_OUTPUT_CREATE_DATE = "CreateDate";
	private static final String SHIPMENT_OUTPUT_CURRENT_FACILITY_NAME = "CurrentFacilityName";
	private static final String SHIPMENT_OUTPUT_SELLER_NAME = "SellerName";
	private static final String SHIPMENT_OUTPUT_BUYER_NAME = "BuyerName";
	private static final String SHIPMENT_OUTPUT_SHIPMENT_ID = "ShipmentID";

	/**
	 * Insert the passed shipment into database.
	 * 
	 * @param shipment
	 * @author Tariq
	 */
	@Override
	public boolean insertShipment(Shipment shipment) {

		Connection connection = null;
		PreparedStatement ps = null;
		try {

			connection = ConnectionManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement("INSERT INTO ShipmentH VALUES (?, ?, ?, ?, ?, ?)");

			ps.setLong(1, shipment.getID());
			ps.setLong(2, shipment.getSellerID());
			ps.setLong(3, shipment.getBuyerID());
			ps.setLong(4, shipment.getFromFacilityID());
			ps.setLong(5, shipment.getToFacilityID());
			ps.setTimestamp(6, new Timestamp(Calendar.getInstance().getTimeInMillis()));

			int i = ps.executeUpdate();

			for (Item item : shipment.getItems()) {
				ps = connection.prepareStatement("INSERT INTO ShipmentD VALUES (?, ?)");
				ps.setLong(1, shipment.getID());
				ps.setLong(2, item.getID());
				i = ps.executeUpdate();
			}
			connection.commit();

			if (i == 1) {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				connection.setAutoCommit(true);
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Get All shipments in the specific page and for specific facility if -1 is
	 * received in facilityID parameter gets all shipments for all facilities
	 * 
	 * @param facilityID
	 * @param pageIndex
	 * @return List of ShipmentOutput object
	 * @author Tariq
	 */
	@Override
	public List<ShipmentOutput> getAllShipments(long facilityID, long pageIndex) {
		Connection connection = null;
		List<ShipmentOutput> shipments = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionManager.getInstance().getConnection();

			if (facilityID != -1) {
				ps = connection.prepareStatement("SELECT " + "SH.ID ShipmentID,CreateDate,"
						+ "sel.name as SellerName,buy.name as BuyerName," + " fafrom.Name as CurrentFacilityName "
						+ "FROM shipmentH SH " + "left join seller sel on (sel.ID = sh.SellerID) "
						+ "left join Buyer buy on (buy.id = sh.BuyerID) "
						+ "left join Facility fafrom on (fafrom.id = sh.FromFacilityID) "
						+ "WHERE fafrom.ID IN ( ? ) ORDER BY ShipmentID");
				ps.setLong(1, facilityID);
			} else {
				ps = connection.prepareStatement("SELECT " + "SH.ID ShipmentID,CreateDate,"
						+ "sel.name as SellerName,buy.name as BuyerName," + " fafrom.Name as CurrentFacilityName "
						+ "FROM shipmentH SH " + "left join seller sel on (sel.ID = sh.SellerID) "
						+ "left join Buyer buy on (buy.id = sh.BuyerID) "
						+ "left join Facility fafrom on (fafrom.id = sh.FromFacilityID) "
						+ "ORDER BY ShipmentID LIMIT 20 OFFSET ?");

				ps.setLong(1, pageIndex);
			}

			ResultSet rs = ps.executeQuery();
			shipments = new ArrayList<ShipmentOutput>();
			while (rs.next()) {
				shipments.add(extractShipmentFromResultSet(rs));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return shipments;
	}

	/**
	 * Extract ShipmentOutput object From ResultSet
	 * 
	 * @param ResultSet
	 * @return ShipmentOutput object
	 * @author Tariq
	 */
	private ShipmentOutput extractShipmentFromResultSet(ResultSet rs) throws SQLException {
		ShipmentOutput shipment = new ShipmentOutput();
		shipment.setShipmentID(rs.getLong(SHIPMENT_OUTPUT_SHIPMENT_ID));
		shipment.setBuyerName(rs.getString(SHIPMENT_OUTPUT_BUYER_NAME));
		shipment.setSellerName(rs.getString(SHIPMENT_OUTPUT_SELLER_NAME));
		shipment.setCurrentFacilityName(rs.getString(SHIPMENT_OUTPUT_CURRENT_FACILITY_NAME));
		shipment.setCreateDate(rs.getDate(SHIPMENT_OUTPUT_CREATE_DATE));
		return shipment;
	}

}
