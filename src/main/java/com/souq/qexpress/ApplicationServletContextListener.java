package com.souq.qexpress;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.souq.qexpress.dao.ConnectionManager;

public class ApplicationServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/**
	 * 
	 * Initialises DB Connection Manager on webapp startup
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ConnectionManager.getInstance().getConnection();
	}

}
