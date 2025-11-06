package com.expense.expensemanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.expensemanager.model.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    // Method to find expenses by category ID
    List<Expense> findByCategoryId(UUID categoryId);
    
    // Method to count expenses by category ID
    long countByCategoryId(UUID categoryId);
    
    // Find expenses by user
    List<Expense> findByUserIdOrderByDateDesc(UUID userId);
    
    // Find expenses by date range
    List<Expense> findByDateBetweenOrderByDateDesc(LocalDate startDate, LocalDate endDate);
    
    // Find expenses by user and date range
    List<Expense> findByUserIdAndDateBetweenOrderByDateDesc(UUID userId, LocalDate startDate, LocalDate endDate);
}

