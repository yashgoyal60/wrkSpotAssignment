package com.wrkspotassignment.service;

import com.wrkspotassignment.exceptions.CustomerCreationException;
import com.wrkspotassignment.model.dto.Address;
import com.wrkspotassignment.model.dto.Customer;
import com.wrkspotassignment.repository.AddressRepository;
import com.wrkspotassignment.repository.CustomerRepository;
import com.wrkspotassignment.service.kafka.KafkaCustomerProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.wrkspotassignment.constants.ErrorMessages.CAUGHT_EXCEPTION;
import static com.wrkspotassignment.constants.ErrorMessages.CUSTOMER_LIST_SAVE_RECEIVED;
import static com.wrkspotassignment.constants.Messages.CUSTOMER_LIST_RECEIVED;

@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private KafkaCustomerProducer kafkaCustomerProducer;

    @Transactional
    private List<Customer> saveCustomers(List<Customer> customerList) {
        return customerRepository.saveAll(customerList);
    }
    public List<Customer> createCustomer(List<Customer> customerList) {
        log.info(CUSTOMER_LIST_RECEIVED + customerList.size());
        List<Customer> savedCustomerList = Collections.emptyList();
        try {
            for (Customer customer: customerList) {
                for (Address address :  customer.getAddress()) {
                    address.setCustomers(customer);
                }
            }
             savedCustomerList = saveCustomers(customerList);
             kafkaCustomerProducer.sendCustomerCreationEvent(savedCustomerList);

        } catch (Exception e) {
            log.error(CAUGHT_EXCEPTION+  e);
            throw new CustomerCreationException(CUSTOMER_LIST_SAVE_RECEIVED);
        }
        return savedCustomerList;
    }

    public List<Customer> getCustomerByFilters(String firstName, String city, String state) {
        if(!firstName.isEmpty())
            return customerRepository.getCustomerByFirstName(firstName);
        if(!city.isEmpty())
            return customerRepository.getCustomerByCity(city);
        if(!state.isEmpty())
             return customerRepository.getCustomerByState(state);
        return customerRepository.findAll();

    }


}
