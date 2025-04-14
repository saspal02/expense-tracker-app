package com.saswat.Expense_service.repository;

import com.saswat.Expense_service.entities.Expense;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends CrudRepository<Expense,Long> {
    List<Expense> findByUserId(String userId);
    List<Expense> findByUserIdAndCreatedAtBetween(String userId, Timestamp timestamp,Timestamp endTime);
    Optional<Expense> findByUserIdAndExternalId(String userId,String externalId);

}
