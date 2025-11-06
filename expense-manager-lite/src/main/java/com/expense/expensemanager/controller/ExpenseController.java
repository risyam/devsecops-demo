package com.expense.expensemanager.controller;

import com.expense.expensemanager.model.Expense;
import com.expense.expensemanager.model.User;
import com.expense.expensemanager.security.SecurityUtils;
import com.expense.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        User currentUser = SecurityUtils.getCurrentUser();
        return service.addExpense(expense, currentUser);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return service.getAllExpenses();
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable UUID id) {
        User currentUser = SecurityUtils.getCurrentUser();
        service.deleteExpense(id, currentUser);
    }
}
