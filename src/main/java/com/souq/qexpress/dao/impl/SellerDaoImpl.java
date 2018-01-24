package com.souq.qexpress.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.souq.qexpress.dao.ConnectionManager;
import com.souq.qexpress.business.Seller;
import com.souq.qexpress.dao.SellerDao;

public class SellerDaoImpl implements SellerDao {

	private static final String SELLER_CITY_ID = "CityID";
	private static final String SELLER_NAME = "Name";
	private static final String SELLER_PHONE = "Phone";
	private static final String SELLER_ID = "ID";

	/**
	 * Get All Seller
	 * 
	 * @return List of Seller object
	 * @author Tariq
	 */
	@Override
	public List<Seller> getAllSeller() {

		List<Seller> sellerList = null;
		try (Connection connection = ConnectionManager.getInstance().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM SELLER");) {

			sellerList = new ArrayList<Seller>();
			while (rs.next()) {
				sellerList.add(extractSellerFromResultSet(rs));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return sellerList;
	}

	/**
	 * Extract Seller object From ResultSet
	 * 
	 * @param ResultSet
	 * @return Seller object
	 * @author Tariq
	 */
	private Seller extractSellerFromResultSet(ResultSet rs) throws SQLException {
		Seller seller = new Seller();
		seller.setID(rs.getLong(SELLER_ID));
		seller.setPhoneNumber(rs.getString(SELLER_PHONE));
		seller.setName(rs.getString(SELLER_NAME));
		seller.setCityID(rs.getInt(SELLER_CITY_ID));

		return seller;
	}

}
