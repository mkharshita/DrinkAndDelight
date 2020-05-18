package org.cap.drinkanddelightmgt.service;

import java.util.List;

import javax.persistence.EntityManager;
import org.cap.drinkanddelightmgt.entities.SupplierEntity;
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
@Import(SupplierServiceImp.class)
class SupplierServiceImpTests {
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ISupplierService service;


	@Test
	public void test_addSupplier() {
		String name = "Harshita";
		String address = "moradabad";
		String phoneNo = "94167329";
		SupplierEntity supplier = new SupplierEntity();
		supplier.setSupplierName(name);
		supplier.setSupplierAddress(address);
		supplier.setSupplierPhoneNumber(phoneNo);
		SupplierEntity supplier2 = service.add(supplier);
		List<SupplierEntity> list = entityManager.createQuery("from SupplierEntity").getResultList();
		Assertions.assertEquals(1, list.size());
		SupplierEntity expected = list.get(0);
		Assertions.assertEquals(expected, supplier2);
		Assertions.assertEquals(name, expected.getSupplierName());
		System.out.println("Supplier Added Successfully!");
	}
	
	// when id is wrong or supplier doesn't exist.
		@Test
		public void test_fetchSupplierById() {
			Executable executable = () -> service.findById("222");
			Assertions.assertThrows(SupplierNotFoundException.class, executable);
		}

		// when id is correct and supplier is fetched.
		@Test
		public void test_fetchSupplierById2() {
			String id="1";
			String name = "Harshita";
			String address = "moradabd";
			String phoneNo = "1345667";
			SupplierEntity supplier = new SupplierEntity();
			supplier.setSupplierId(id);
			supplier.setSupplierName(name);
			supplier.setSupplierAddress(address);
			supplier.setSupplierPhoneNumber(phoneNo);
			supplier = entityManager.merge(supplier);

			String id2 = supplier.getSupplierId();
			SupplierEntity supplier2 = service.findById(id2);
			Assertions.assertEquals(supplier, supplier2);
			Assertions.assertEquals(name, supplier.getSupplierName());
		}
	

}
