package com.souq.qexpress.dao;

import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionManager {

	// TODO move to properties file
	private static final String URL = "jdbc:mysql://localhost:3306/Shipment_DB2";
	private static final String USER = "root";
	private static final String PASS = "";

	private ComboPooledDataSource dataSource;
	private static ConnectionManager instance = new ConnectionManager();

	private ConnectionManager() {
		init();
	}

	/**
	 * Get a connection from connection pool
	 * 
	 * @return Connection object
	 * @author Tariq
	 */
	public Connection getConnection() {
		try {

			return dataSource.getConnection();

		} catch (SQLException ex) {
			throw new RuntimeException("Error connecting to the database", ex);
		}
	}

	public static ConnectionManager getInstance() {

		return instance;
	}

	/**
	 * To initialize the connection manager.
	 * 
	 * @author Tariq
	 */
	private void init() {
		dataSource = new ComboPooledDataSource();
		dataSource.setJdbcUrl(URL);
		dataSource.setUser(USER);
		dataSource.setPassword(PASS);
		dataSource.setInitialPoolSize(5);
		dataSource.setMinPoolSize(2);
		dataSource.setAcquireIncrement(5);
		dataSource.setMaxPoolSize(10);
	}

}
