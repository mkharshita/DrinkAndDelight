package org.cap.drinkanddelightmgt.service;

import java.util.List;

import org.cap.drinkanddelightmgt.entities.DistributorEntity;
import org.cap.drinkanddelightmgt.entities.SupplierEntity;

public interface ISupplierService 
{
	List<SupplierEntity> fetchAllSupplier();
	SupplierEntity add(SupplierEntity supplierEntity);
	SupplierEntity findById(String id);
}
