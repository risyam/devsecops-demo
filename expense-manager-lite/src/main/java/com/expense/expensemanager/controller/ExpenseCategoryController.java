package com.expense.expensemanager.controller;

import com.expense.expensemanager.model.ExpenseCategory;
import com.expense.expensemanager.model.User;
import com.expense.expensemanager.security.SecurityUtils;
import com.expense.expensemanager.service.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class ExpenseCategoryController {

    @Autowired
    private ExpenseCategoryService service;

    @PostMapping
    public ExpenseCategory addCategory(@RequestBody ExpenseCategory category) {
        User currentUser = SecurityUtils.getCurrentUser();
        return service.addCategory(category, currentUser);
    }

    @GetMapping
    public List<ExpenseCategory> getAllCategories() {
        User currentUser = SecurityUtils.getCurrentUser();
        return service.getAllCategories(currentUser);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable UUID id) {
        User currentUser = SecurityUtils.getCurrentUser();
        service.deleteCategory(id, currentUser);
    }
}

