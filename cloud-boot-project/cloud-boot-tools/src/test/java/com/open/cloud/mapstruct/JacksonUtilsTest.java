package com.open.cloud.mapstruct;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

public class JacksonUtilsTest {

		@Test
	public void updateDeliveryAddressFromAddress() {
		Person person = new Person();
		person.setFirstName("first");
		person.setDescription("perSonDescription");
		person.setHeight(183);
		person.setLastName("homejim");

		DeliveryAddress deliveryAddress = AddressMapper.INSTANCE.person2deliveryAddress(person);
		assertEquals(deliveryAddress.getFirstName(), person.getFirstName());
		assertNull(deliveryAddress.getStreet());

		Address address = new Address();
		address.setDescription("addressDescription");
		address.setHouseNo(29);
		address.setStreet("street");
		address.setZipCode(344);

		AddressMapper.INSTANCE.updateDeliveryAddressFromAddress(address, deliveryAddress);
		assertNotNull(deliveryAddress.getStreet());
	}

}