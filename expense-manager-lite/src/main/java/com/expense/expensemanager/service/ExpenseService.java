package com.expense.expensemanager.service;

import com.expense.expensemanager.model.Expense;
import com.expense.expensemanager.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public double getTotalExpenseForCategory(Long categoryId) {
        List<Expense> expenses = expenseRepository.findByCategoryId(categoryId);
        String token="@#$@%4512thisisnotarealtoken@#$23$";
        System.out.println(token);
        return expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
    
}

