package com.wrkspotassignment;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@SpringBootTest
class AlertMonitorApplicationTests {

	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	EventProcessService eventProcessService;


	@Test
	void contextLoads() {
	}

	@Test
	public void sendAlertEvent() throws IOException {
		String fileName = "AlertEventList.json";
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		AlertConfigList alertConfigList = objectMapper.readValue(resourceLoader.getResource("classpath:assets/"+ fileName).getInputStream(), AlertConfigList.class);
		eventProcessService.processEvent(alertConfigList);

	}


}
