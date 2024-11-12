package com.expense.expensemanager.controller;

import com.expense.expensemanager.model.ExpenseCategory;
import com.expense.expensemanager.service.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
@RequestMapping("/api/categories")
public class ExpenseCategoryController {

    @Autowired
    private ExpenseCategoryService service;

    @PostMapping
    public ExpenseCategory addCategory(@RequestBody ExpenseCategory category) {
        return service.addCategory(category);
    }

    @GetMapping
    public List<ExpenseCategory> getAllCategories() {
        return service.getAllCategories();
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
    }
}

