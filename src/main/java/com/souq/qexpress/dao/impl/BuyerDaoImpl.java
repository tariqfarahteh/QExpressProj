package com.souq.qexpress.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.souq.qexpress.dao.ConnectionManager;
import com.souq.qexpress.business.Buyer;
import com.souq.qexpress.dao.BuyerDao;

public class BuyerDaoImpl implements BuyerDao {

	private static final String BUYER_CITY_ID = "CityID";
	private static final String BUYER_NAME = "Name";
	private static final String BUYER_PHONE = "Phone";
	private static final String BUYER_ID = "ID";

	/**
	 * Get All Buyers
	 * 
	 * @return List of Buyer object
	 * @author Tariq
	 */
	@Override
	public List<Buyer> getAllBuyers() {

		List<Buyer> buyerList = null;
		try (Connection connection = ConnectionManager.getInstance().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM BUYER")) {

			buyerList = new ArrayList<Buyer>();
			while (rs.next()) {
				buyerList.add(extractBuyerFromResultSet(rs));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return buyerList;
	}

	/**
	 * Extract Buyer object From ResultSet
	 * 
	 * @param ResultSet
	 * @return Buyer object
	 * @author Tariq
	 */
	private Buyer extractBuyerFromResultSet(ResultSet rs) throws SQLException {
		Buyer buyer = new Buyer();
		buyer.setID(rs.getLong(BUYER_ID));
		buyer.setPhoneNumber(rs.getString(BUYER_PHONE));
		buyer.setName(rs.getString(BUYER_NAME));
		buyer.setCityID(rs.getInt(BUYER_CITY_ID));

		return buyer;
	}

}
