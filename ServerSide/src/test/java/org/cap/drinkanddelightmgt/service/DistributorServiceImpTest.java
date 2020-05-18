package org.cap.drinkanddelightmgt.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.cap.drinkanddelightmgt.entities.DistributorEntity;
import org.cap.drinkanddelightmgt.exception.DistributorNotFoundException;
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
@Import(DistributorServiceImp.class)
class DistributorServiceImpTest {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private IDistributorService service;


	@Test
	public void test_addDistributor() {
		String name = "Harshita";
		String address = "moradabad";
		String phoneNo = "94167329";
		DistributorEntity distributor = new DistributorEntity();
		distributor.setDistributorName(name);
		distributor.setDistributorAddress(address);
		distributor.setDistributorPhoneNumber(phoneNo);
		DistributorEntity distributor2 = service.add(distributor);
				
		List<DistributorEntity> list = entityManager.createQuery("from DistributorEntity").getResultList();
		Assertions.assertEquals(1, list.size());
		DistributorEntity expected = list.get(0);
		Assertions.assertEquals(expected, distributor2);
		Assertions.assertEquals(name, expected.getDistributorName());
		System.out.println("Supplier Added Successfully!");
	}
	
	// when id is wrong or supplier doesn't exist.
		@Test
		public void test_fetchSupplierById() {
			Executable executable = () -> service.findById("222");
			Assertions.assertThrows(DistributorNotFoundException.class, executable);
		}

		// when id is correct and supplier is fetched.
		@Test
		public void test_fetchSupplierById2() {
			String id="1";
			String name = "Harshita";
			String address = "moradabd";
			String phoneNo = "1345667";
			DistributorEntity distributor = new DistributorEntity();
			distributor.setDistributorId(id);
			distributor.setDistributorName(name);
			distributor.setDistributorAddress(address);
			distributor.setDistributorPhoneNumber(phoneNo);
			distributor = entityManager.merge(distributor);

			String id2 = distributor.getDistributorId();
			DistributorEntity distributor2 = service.findById(id2);
			Assertions.assertEquals(distributor, distributor);
			Assertions.assertEquals(name, distributor.getDistributorName());
		}
	

	
}
