package org.cap.drinkanddelightmgt.dao;

import java.util.Optional;

import org.cap.drinkanddelightmgt.entities.RawMaterialStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRawMaterialStockDao extends JpaRepository<RawMaterialStockEntity,String> {
	 Optional<RawMaterialStockEntity> findByOrderId(String OrderId);
}
