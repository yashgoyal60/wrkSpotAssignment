package com.wrkspotassignment.service.kafka;

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

//        CompletableFuture<SendResult<String, List<Customer>>> future = customerKafkaTemplate.send(kafkaCustomerTopic, customerList);
        SendResult<String, List<Customer>> sendResult = customerKafkaTemplate.send(kafkaCustomerTopic, customerList).get(2, TimeUnit.SECONDS);
//        future.whenComplete((result, ex) -> {
//            if (ex == null) {
//                System.out.println("Asd");
////                handleSuccess(data);
//            }
//            else {
//                System.out.println("Asd");
////                handleFailure(data, record, ex);
//            }
//        });
        log.info("Send customerList event sent via Kafka size: {}", customerList.size());
//        log.info(future.get().toString());
        return true;
    }
}
