package org.cap.drinkanddelightmgt.dao;

import org.cap.drinkanddelightmgt.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierDao extends JpaRepository<SupplierEntity,String> {

}
