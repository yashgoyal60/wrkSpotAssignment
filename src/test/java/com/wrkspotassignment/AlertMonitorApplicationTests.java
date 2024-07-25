package com.wrkspotassignment;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrkspotassignment.model.dto.Customer;
import com.wrkspotassignment.repository.AddressRepository;
import com.wrkspotassignment.repository.CustomerRepository;
import com.wrkspotassignment.service.CustomerService;
import com.wrkspotassignment.service.kafka.KafkaCustomerProducer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class AlertMonitorApplicationTests {

	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	ResourceLoader resourceLoader;



	@Test
	void contextLoads() {
	}

	@Test
	public void sendAlertEvent() throws IOException {
		String fileName = "CustomerCreationRequest.json";
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Customer customer = objectMapper.readValue(resourceLoader.getResource("classpath:assets/" + fileName).getInputStream(), Customer.class);

	}


}
