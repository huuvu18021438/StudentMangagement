package io.demo.student_management.repository;

import io.demo.student_management.model.Address;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends R2dbcRepository<Address, Long> {
}
