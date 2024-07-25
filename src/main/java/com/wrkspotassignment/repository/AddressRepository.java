package com.wrkspotassignment.repository;

import com.wrkspotassignment.model.dto.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
