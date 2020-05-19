package org.cap.drinkanddelightmgt.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.cap.drinkanddelightmgt.dao.IDistributorDao;
import org.cap.drinkanddelightmgt.entities.DistributorEntity;
import org.cap.drinkanddelightmgt.exception.DistributorNotFoundException;
import org.cap.drinkanddelightmgt.exception.EmptyDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DistributorServiceImp implements IDistributorService {

	@Autowired
	private IDistributorDao distributorDao;
	
	
	/*******************************************************************************************************
	 - Function Name	:	add
	 - Input Parameters	:	DistributorEntity
	 - Return Type		:	DistributorEntity
	 - Description		:	adding the distributor in the database
	 ********************************************************************************************************/
	@Override
	public DistributorEntity add(DistributorEntity distributorEntity) {
		if(distributorEntity.getDistributorName().equals(null) && distributorEntity.getDistributorAddress().equals(null)
				&& distributorEntity.getDistributorPhoneNumber().equals(null)) {
			throw new EmptyDataException("Input Data is Missing");
		}
		else 
			distributorEntity.setDistributorId(generatedId(8));
		distributorEntity=distributorDao.save(distributorEntity);
		return distributorEntity;
	}


	/*******************************************************************************************************
	 - Function Name	:	generatedId
	 - Input Parameters	:	int
	 - Return Type		:	String
	 - Description		:	generating the id for the distributor
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
	 - Return Type		:	DistributorEntity
	 - Description		:	finding the distributor by its id and throw exception if distributor not found
	 ********************************************************************************************************/
	@Override
	public DistributorEntity findById(String id) {
		if(id.isEmpty()) {
			throw new EmptyDataException("Id Missing");
		}
			Optional<DistributorEntity>optional=distributorDao.findById(id);
		     if(optional.isPresent()){
		    	 DistributorEntity supplier=optional.get();
		         return supplier;
		     }
		     throw new DistributorNotFoundException("Distributor not found for id="+id);
		    
		}

	
	/*******************************************************************************************************
	 - Function Name	:	fetchAllDistributor
	 - Input Parameters	:	---
	 - Return Type		:	List<DistributorEntity>
	 - Description		:	fetching all the distributors from the database
	 ********************************************************************************************************/
	@Override
	public List<DistributorEntity> fetchAllDistributor() {
		List<DistributorEntity> distributors = distributorDao.findAll();
		return distributors;
	}
		
	    
}




