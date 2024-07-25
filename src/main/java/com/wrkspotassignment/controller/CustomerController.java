package com.wrkspotassignment.controller;

import com.wrkspotassignment.model.dto.Customer;
import com.wrkspotassignment.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

        @PostMapping("create")
    public List<Customer> createCustomer(@RequestBody List<Customer> customerList) {
        return customerService.createCustomer(customerList);

    }


}
