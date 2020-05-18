package org.cap.drinkanddelightmgt.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.cap.drinkanddelightmgt.entities.RawMaterialStockEntity;
import org.cap.drinkanddelightmgt.entities.SupplierEntity;
import org.cap.drinkanddelightmgt.exception.RawMaterialStockNotFoundException;
import org.cap.drinkanddelightmgt.exception.SupplierNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest// for jpa tests
@ExtendWith(SpringExtension.class)// integrate spring test framework with junit5
@Import(RawMaterialServiceImp.class)

class RawMaterialStockServiceImpTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private IRawMaterialStockService service;


	@Test
	public void test_addRawMaterialStock() {
		String orderId="111";
		String name = "wood";
		double price_per_unit=10;
		double quantityValue=10;
		double quantityUnit=10;
		String warehouseId="121";
		RawMaterialStockEntity stock=new RawMaterialStockEntity();
		stock.setOrderId(orderId);
		stock.setName(name);
		stock.setPrice_per_unit(price_per_unit);
		stock.setQuantityValue(quantityValue);
		stock.setQuantityUnit(quantityUnit);
		stock.setWarehouseId(warehouseId);
		RawMaterialStockEntity stock2=service.add(stock);
		List<RawMaterialStockEntity> list = entityManager.createQuery("from RawMaterialStockEntity").getResultList();
		Assertions.assertEquals(1, list.size());
		RawMaterialStockEntity expected = list.get(0);
		Assertions.assertEquals(expected, stock2);
		Assertions.assertEquals(name, expected.getName());
		System.out.println("RawMaterialStock Added Successfully!");
	}
	

	// when id is wrong or stock doesn't exist.
			@Test
			public void test_fetchSupplierById() {
				Executable executable = () -> service.trackRawMaterialOrder("111");
				Assertions.assertThrows(RawMaterialStockNotFoundException.class, executable);
			}
			
			// when id is correct and stock is fetched.
			@Test
			public void test_fetchSupplierById2() {
				String stockId="1";
				String orderId="111";
				String name = "wood";
				double price_per_unit=10;
				double quantityValue=10;
				double quantityUnit=10;
				String warehouseId="121";
				RawMaterialStockEntity stock=new RawMaterialStockEntity();
				stock.setStockId(stockId);
				stock.setOrderId(orderId);
				stock.setName(name);
				stock.setPrice_per_unit(price_per_unit);
				stock.setQuantityValue(quantityValue);
				stock.setQuantityUnit(quantityUnit);
				stock.setWarehouseId(warehouseId);
				stock = entityManager.merge(stock);

				String id2 = stock.getOrderId();
				RawMaterialStockEntity stock2 = service.trackRawMaterialOrder(id2);
				Assertions.assertEquals(stock, stock2);
				Assertions.assertEquals(name, stock.getName());
			}

}
