package com.expense.expensemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.expensemanager.model.ExpenseCategory;
import com.expense.expensemanager.model.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, UUID> {
    List<ExpenseCategory> findByUserOrderByNameAsc(User user);
}

