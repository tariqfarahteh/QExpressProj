package com.souq.qexpress.dao;

import java.util.List;
import com.souq.qexpress.business.Shipment;
import com.souq.qexpress.business.ShipmentOutput;

public interface ShipmentDao {

	boolean insertShipment(Shipment shipment);

	List<ShipmentOutput> getAllShipments(long facilityID, long pageIndex);
}
