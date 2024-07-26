package com.wrkspotassignment.service.kafka;

import com.wrkspotassignment.exceptions.CustomerCreationException;
import com.wrkspotassignment.model.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.wrkspotassignment.constants.ErrorMessages.CAUGHT_EXCEPTION;
import static com.wrkspotassignment.constants.ErrorMessages.KAFKA_FAILED_EXCEPTION;
import static com.wrkspotassignment.constants.Messages.CUSTOMER_EVENT_SENT;

@Service
public class KafkaCustomerProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaCustomerProducer.class);

    private final KafkaTemplate<String, List<Customer>> customerKafkaTemplate;


    private final String kafkaCustomerTopic;


    public KafkaCustomerProducer(KafkaTemplate<String, List<Customer>> customerKafkaTemplate, @Value("${spring.kafka.order.topic.customer-producer}") String kafkaCustomerTopic) {
        this.customerKafkaTemplate = customerKafkaTemplate;
        this.kafkaCustomerTopic = kafkaCustomerTopic;
    }

    public boolean sendCustomerCreationEvent(List<Customer> customerList) throws ExecutionException, InterruptedException, TimeoutException {
        try {
            SendResult<String, List<Customer>> sendResult = customerKafkaTemplate.send(kafkaCustomerTopic, customerList).get(2, TimeUnit.SECONDS);
            log.info(CUSTOMER_EVENT_SENT, customerList.size());
        } catch (Exception e) {
            log.error(KAFKA_FAILED_EXCEPTION);
            log.error(CAUGHT_EXCEPTION +  e);
            throw new CustomerCreationException(KAFKA_FAILED_EXCEPTION);
        }
        return true;
    }
}
