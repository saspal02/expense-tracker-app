package com.saswat.Expense_service.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saswat.Expense_service.dto.ExpenseDto;
import com.saswat.Expense_service.entities.Expense;
import com.saswat.Expense_service.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public boolean createExpense(ExpenseDto expenseDto) {
        setCurrency(expenseDto);
        try {
            expenseRepository.save(objectMapper.convertValue(expenseDto, Expense.class));
            return true;

        }catch (Exception ex) {
            return false;
        }
    }

    public boolean updateExpense(ExpenseDto expenseDto) {
        Optional<Expense> expenseFoundOpt = expenseRepository.findByUserIdAndExternalId(expenseDto.getUserId(),expenseDto.getExternalId());
        if (expenseFoundOpt.isEmpty()) {
            return false;
        }
        Expense expense = expenseFoundOpt.get();
        expense.setCurrency(Strings.isNotBlank(expenseDto.getCurrency())? expenseDto.getCurrency() : expense.getCurrency());
        expense.setMerchant(Strings.isNotBlank(expenseDto.getMerchant())? expenseDto.getMerchant(): expense.getMerchant());
        expense.setAmount(expenseDto.getAmount());
        expenseRepository.save(expense);
        return true;

    }

    public List<ExpenseDto> getExpense(String userId) {
        List<Expense> expenseList = expenseRepository.findByUserId(userId);
        return objectMapper.convertValue(expenseList, new TypeReference<List<ExpenseDto>>() {
        });
    }

    private void setCurrency(ExpenseDto expenseDto) {
        if (Objects.isNull(expenseDto.getCurrency())) {
            expenseDto.setCurrency("inr");
        }
    }
}
