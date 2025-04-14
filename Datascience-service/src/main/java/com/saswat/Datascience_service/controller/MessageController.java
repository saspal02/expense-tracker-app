package com.saswat.Datascience_service.controller;

import com.saswat.Datascience_service.entities.Expense;
import com.saswat.Datascience_service.entities.MessageRequestDto;
import com.saswat.Datascience_service.eventProducer.ExpenseProducer;
import com.saswat.Datascience_service.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MessageController {

    private final MessageService messageService;
    private final ExpenseProducer expenseProducer;

    @Autowired
    public MessageController(MessageService messageService, ExpenseProducer expenseProducer) {
        this.messageService = messageService;
        this.expenseProducer = expenseProducer;
    }

    @PostMapping("/v1/ds/message")
    public Expense handleMessage(@RequestBody MessageRequestDto request) throws IOException {
        Expense result = messageService.processMessage(request.getMessage());
        try {
            expenseProducer.sendEventToKafka(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @GetMapping("/")
    public String handleGet() {
        return "Hello world";
    }
}
