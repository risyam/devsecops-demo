package com.expense.expensemanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.expensemanager.model.Expense;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // Method to find expenses by category ID
    List<Expense> findByCategoryId(Long categoryId);
}

