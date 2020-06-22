/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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