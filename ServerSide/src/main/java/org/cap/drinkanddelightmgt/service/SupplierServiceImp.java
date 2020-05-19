package org.cap.drinkanddelightmgt.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.cap.drinkanddelightmgt.dao.ISupplierDao;
import org.cap.drinkanddelightmgt.entities.SupplierEntity;
import org.cap.drinkanddelightmgt.exception.EmptyDataException;
import org.cap.drinkanddelightmgt.exception.SupplierNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SupplierServiceImp implements ISupplierService {
	@Autowired
	private ISupplierDao suppliersDao;

	/*******************************************************************************************************
	 - Function Name	:	add
	 - Input Parameters	:	SupplierEntity
	 - Return Type		:	SupplierEntity
	 - Description		:	adding the supplier in the database
	 ********************************************************************************************************/
	@Override
	public SupplierEntity add(SupplierEntity supplierEntity) {
		if(supplierEntity.getSupplierName().equals(null) && supplierEntity.getSupplierAddress().equals(null)
				&& supplierEntity.getSupplierPhoneNumber().equals(null)) {
			throw new EmptyDataException("Input Data is Missing");
		}
		else
			supplierEntity.setSupplierId(generatedId(9));
		supplierEntity=suppliersDao.save(supplierEntity);
		return supplierEntity;
	}
	
	

	/*******************************************************************************************************
	 - Function Name	:	generatedId
	 - Input Parameters	:	int
	 - Return Type		:	String
	 - Description		:	generating the id for the supplier
	 ********************************************************************************************************/
	String generatedId(int digits) {
		StringBuilder id=new StringBuilder();
		for(int i=0;i<digits;i++)
		{
			Random random=new Random();
			int num=random.nextInt(9);
			id.append(num);
		}
		return id.toString();
	}
	
	
	/*******************************************************************************************************
	 - Function Name	:	findById
	 - Input Parameters	:	String
	 - Return Type		:	SupplierEntity
	 - Description		:	finding the supplier by its id and throw exception if supplier not found
	 ********************************************************************************************************/
	@Override
	public SupplierEntity findById(String id) {
		if(id.isEmpty()) {
			throw new EmptyDataException("Id Missing");
		}
		Optional<SupplierEntity>optional=suppliersDao.findById(id);
	     if(optional.isPresent()){
	         SupplierEntity supplierEntity=optional.get();
	         return supplierEntity;
	     }
	     throw new SupplierNotFoundException("supplier not found for id="+id);
	}

	
	/*******************************************************************************************************
	 - Function Name	:	fetchAllSupplier
	 - Input Parameters	:	---
	 - Return Type		:	List<SupplierEntity>
	 - Description		:	fetching all the suppliers from the database
	 ********************************************************************************************************/
	@Override
	public List<SupplierEntity> fetchAllSupplier() {
		List<SupplierEntity> suppliers=suppliersDao.findAll();
		return suppliers;
	}
}




