package com.wrkspotassignment;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrkspotassignment.model.dto.Customer;
import com.wrkspotassignment.repository.CustomerRepository;
import com.wrkspotassignment.service.CustomerService;
import com.wrkspotassignment.service.kafka.KafkaCustomerProducer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class CustomerCreationTest {

	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	ResourceLoader resourceLoader;


	@Autowired
	CustomerService customerService ;

	@MockBean
	CustomerRepository customerRepository;
	@MockBean
	KafkaCustomerProducer kafkaCustomerProducer;


	@Test
	void contextLoads() {
	}

	@Test
	public void customerCreation_Pass() throws IOException, ExecutionException, InterruptedException, TimeoutException {
		String fileName = "CustomerCreationRequest.json";
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Customer customer = objectMapper.readValue(resourceLoader.getResource("classpath:assets/" + fileName).getInputStream(), Customer.class);
		Mockito.when(kafkaCustomerProducer.sendCustomerCreationEvent(List.of(customer))).thenReturn(Mockito.anyBoolean());
		Mockito.when(customerRepository.saveAll(List.of(customer))).thenReturn(List.of(customer));
		List<Customer> result = customerService.createCustomer(List.of(customer));

	}


}
