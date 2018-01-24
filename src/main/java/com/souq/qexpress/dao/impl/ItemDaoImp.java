package com.souq.qexpress.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.souq.qexpress.dao.ConnectionManager;
import com.souq.qexpress.business.Item;
import com.souq.qexpress.dao.ItemDao;

public class ItemDaoImp implements ItemDao {

	private static final String ITEM_SELLER_ID = "SellerID";
	private static final String ITEM_DESCRIPTION = "Description";
	private static final String ITEM_ID = "id";

	/**
	 * Get Item object
	 * 
	 * @param itemID
	 * @return Item object
	 * @author Tariq
	 */
	@Override
	public Item getItem(long itemID) {

		try (Connection connection = ConnectionManager.getInstance().getConnection();
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM item WHERE id = ?")) {

			stmt.setLong(1, itemID);
			try (ResultSet rs = stmt.executeQuery()) {

				if (rs.next()) {
					return extractItemFromResultSet(rs);
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Extract Item object From ResultSet
	 * 
	 * @param ResultSet
	 * @return Item object
	 * @author Tariq
	 */
	private Item extractItemFromResultSet(ResultSet rs) throws SQLException {
		Item item = new Item();
		item.setID(rs.getLong(ITEM_ID));
		item.setDescription(rs.getString(ITEM_DESCRIPTION));
		item.setSellerID(rs.getLong(ITEM_SELLER_ID));

		return item;
	}

}
