package com.saswat.Expense_service.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saswat.Expense_service.dto.ExpenseDto;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ExpenseDeserializer implements Deserializer<ExpenseDto> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public ExpenseDto deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        ExpenseDto expense = null;
        try {
            expense = mapper.readValue(bytes, ExpenseDto.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return expense;
    }

    @Override
    public void close() {

    }
}
