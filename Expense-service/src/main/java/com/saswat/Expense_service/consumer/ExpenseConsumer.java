package com.saswat.Expense_service.consumer;

import com.saswat.Expense_service.dto.ExpenseDto;
import com.saswat.Expense_service.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseConsumer {
    private ExpenseService expenseService;

    @Autowired
    ExpenseConsumer(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @KafkaListener(topics="${spring.kafka.topic-json.name}", groupId ="${spring.kafka.consumer.group-id}")
    public void listen(ExpenseDto eventData) {
        try {
            System.out.println("Received Kafka message: " + eventData);
            expenseService.createExpense(eventData);
        } catch (Exception ex) {
            System.out.println("Exception in listening the event");
        }
    }
}
