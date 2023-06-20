package io.demo.student_management.mapper;

import io.demo.student_management.model.Address;
import io.demo.student_management.rest.reource.AddressResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressResource toResource(Address address);
}
