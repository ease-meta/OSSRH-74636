package com.open.cloud.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

	AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

	/**
	 * Person->DeliveryAddress, 缺失地址信息
	 * @param person
	 * @return
	 */
	DeliveryAddress person2deliveryAddress(Person person);

	/**
	 * 更新， 使用 Address 来补全 DeliveryAddress 信息。 注意注解 @MappingTarget
	 * @param address
	 * @param deliveryAddress
	 */
	void updateDeliveryAddressFromAddress(Address address,
										  @MappingTarget DeliveryAddress deliveryAddress);
	
	@Mapping(source = "person.description", target = "description")
	@Mapping(source = "address.houseNo", target = "houseNumber")
	DeliveryAddress personAndAddressToDeliveryAddressDto(Person person, Address address);
}