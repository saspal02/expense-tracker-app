package com.saswat.Datascience_service.service;

import com.saswat.Datascience_service.entities.Expense;
import com.saswat.Datascience_service.utils.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageService {
    private final MessagesUtil messagesUtil;
    private final LLMService llmService;

    @Autowired
    public MessageService(MessagesUtil messagesUtil, LLMService llmService) {
        this.messagesUtil = messagesUtil;
        this.llmService = llmService;
    }

    public Expense processMessage(String message) throws IOException {
        if(messagesUtil.isBankSms(message)) {
            return llmService.extractExpense(message);
        } else {
            return null;
        }
    }
}
