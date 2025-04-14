package com.saswat.Datascience_service.service;

import com.saswat.Datascience_service.entities.Expense;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class LLMService {
    private static final String SYSTEM_PROMPT =
            "You are an expert extraction algorithm" +
            "Only extract relevant information from the text." +
            "If you do not know the value of an attribute asked to extract," +
            "return null for the attribute's value.";

    private final ChatClient chatClient;

    public LLMService(ChatClient chatClient) {
        this.chatClient = chatClient;

    }

    public Expense extractExpense(String content) {
        if (content.isEmpty()) {
            return null;
        }
        return chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(content)
                .call()
                .entity(Expense.class);

    }


}
