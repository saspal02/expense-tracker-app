package com.saswat.Datascience_service.eventProducer;

import com.saswat.Datascience_service.entities.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExpenseProducer {
    private final KafkaTemplate<String, Expense> kafkaTemplate;

    @Value("${spring.kafka.topic-json.name}")
    private String topicName;

    @Autowired
    public ExpenseProducer(KafkaTemplate<String, Expense> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEventToKafka(Expense expense) {
        kafkaTemplate.send(topicName,expense);
        System.out.println("Send Kafka event to consumer");
    }
}
