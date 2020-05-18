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

	@Override
	public List<SupplierEntity> fetchAllSupplier() {
		List<SupplierEntity> suppliers=suppliersDao.findAll();
		return suppliers;
	}
}


