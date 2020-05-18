package org.cap.drinkanddelightmgt.service;

import java.util.List;

import org.cap.drinkanddelightmgt.entities.DistributorEntity;

public interface IDistributorService {
	List<DistributorEntity> fetchAllDistributor();
	DistributorEntity add(DistributorEntity distributorEntity);
	DistributorEntity findById(String id);

}
