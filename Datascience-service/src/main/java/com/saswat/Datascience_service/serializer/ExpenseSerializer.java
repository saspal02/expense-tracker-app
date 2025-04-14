package com.saswat.Datascience_service.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saswat.Datascience_service.entities.Expense;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class ExpenseSerializer implements Serializer<Expense>
{
    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public byte[] serialize(String arg0, Expense arg1) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(arg1).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }
    @Override public void close() {
    }
}