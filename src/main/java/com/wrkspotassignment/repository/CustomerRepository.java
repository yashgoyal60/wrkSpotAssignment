package com.wrkspotassignment.repository;

import com.wrkspotassignment.model.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

//    @Query(value = "INSERT into customer")
//    List<Customer> saveAll(List<Customer> customerList);
}
