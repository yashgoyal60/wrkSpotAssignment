package com.wrkspotassignment;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrkspotassignment.model.dto.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@SpringBootTest
class CustomerCreationTest {

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
