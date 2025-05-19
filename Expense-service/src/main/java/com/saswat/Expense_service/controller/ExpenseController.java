package com.saswat.Expense_service.controller;

import com.saswat.Expense_service.dto.ExpenseDto;
import com.saswat.Expense_service.service.ExpenseService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense/v1")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping(path = "/getExpense")
    public ResponseEntity<List<ExpenseDto>> getExpenses(@RequestHeader(value = "user-id") @NonNull String userId) {
        try {
            List<ExpenseDto> expenseDtoList = expenseService.getExpense(userId);
            return new ResponseEntity<>(expenseDtoList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/addExpense")
    public ResponseEntity<Boolean> addExpenses(@RequestHeader(value = "user-id")@NonNull String userId,@RequestBody ExpenseDto expenseDto) {
        try {
            expenseDto.setUserId(userId);
            return new ResponseEntity<>(expenseService.createExpense(expenseDto), HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Boolean> checkHealth(){
        return new ResponseEntity<>(true, HttpStatus.OK);
    }


}
