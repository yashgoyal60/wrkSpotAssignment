package com.wrkspotassignment.repository;

import com.wrkspotassignment.model.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryRewriter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> getCustomerByFirstName(String name);

    @Query(value="SELECT * from customer where customer.customer_id in (SELECT distinct address.customer_id from address where address.state = ?1)",
            nativeQuery = true)
    List<Customer> getCustomerByState(String state);

        @Query(value="SELECT * from customer where customer.customer_id in (SELECT distinct address.customer_id from address where address.city = ?1)",
            nativeQuery = true)
    List<Customer> getCustomerByCity(String city);

}
