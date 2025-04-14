package com.saswat.Datascience_service.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MessagesUtil {

    // List of keywords to detect a bank-related SMS
    private static final String[] WORDS_TO_SEARCH = {"spent", "bank", "card"};

    // Create a regex pattern dynamically based on keywords
    private static final Pattern PATTERN = Pattern.compile(
            "\\b(" + String.join("|", WORDS_TO_SEARCH) + ")\\b",
            Pattern.CASE_INSENSITIVE
    );

    /**
     * Checks if a given message is a bank-related SMS.
     *
     * @param message The SMS content.
     * @return true if the message contains bank-related keywords, false otherwise.
     */
    public boolean isBankSms(String message) {
        if (message == null || message.isEmpty()) {
            return false; // Handle null or empty messages
        }
        Matcher matcher = PATTERN.matcher(message);
        return matcher.find();
    }
}
