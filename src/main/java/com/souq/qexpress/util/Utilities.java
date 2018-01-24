package com.souq.qexpress.util;

import java.util.concurrent.ThreadLocalRandom;

public class Utilities {

	/**
	 * Get Random ID for shipment that contains 13 digit.
	 * 
	 * @return long random number
	 * @author Tariq
	 */
	public static long generateShipmentID() {
		return ThreadLocalRandom.current().nextLong(1000000000000L, 10000000000000L);
	}

}
