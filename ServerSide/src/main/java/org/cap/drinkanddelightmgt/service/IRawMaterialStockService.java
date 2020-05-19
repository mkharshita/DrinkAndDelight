package org.cap.drinkanddelightmgt.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.cap.drinkanddelightmgt.entities.DistributorEntity;
import org.cap.drinkanddelightmgt.entities.RawMaterialStockEntity;

public interface IRawMaterialStockService {
	
	List<RawMaterialStockEntity> fetchAllRawMaterialStock();
	
	RawMaterialStockEntity add(RawMaterialStockEntity stock);
	
	Boolean doesRawMaterialOrderIdExistInStock(String orderId);
	
	RawMaterialStockEntity trackRawMaterialOrder(String orderId);
	
	Boolean processDateCheck(RawMaterialStockEntity rawMaterialStockEntity,Date processDate);
	
	RawMaterialStockEntity setProcessDateInStock(String OrderId,Date processDate);
	
	Boolean validateManufacturingDate(RawMaterialStockEntity rmstock,Date manufacturingDate);
	
	Boolean validateExpiryDate(RawMaterialStockEntity rmstock,Date expirydate);
	
	RawMaterialStockEntity updateRawMaterialStock(RawMaterialStockEntity rmstock, Date expiryDate, Date manufacturingDate,
			String qa);
	
}
