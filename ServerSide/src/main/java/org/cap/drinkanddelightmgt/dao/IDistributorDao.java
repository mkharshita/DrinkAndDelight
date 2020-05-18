package org.cap.drinkanddelightmgt.dao;

import org.cap.drinkanddelightmgt.entities.DistributorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistributorDao extends JpaRepository<DistributorEntity,String> {
	
}
