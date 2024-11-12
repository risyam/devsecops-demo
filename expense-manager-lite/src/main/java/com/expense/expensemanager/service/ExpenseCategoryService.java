package com.expense.expensemanager.service;


import com.expense.expensemanager.model.ExpenseCategory;
import com.expense.expensemanager.repository.ExpenseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseCategoryService {

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategory addCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryRepository.save(expenseCategory);
    }

    public List<ExpenseCategory> getAllCategories() {
        return expenseCategoryRepository.findAll();
    }

    public void deleteCategory(Long id) {
        expenseCategoryRepository.deleteById(id);
    }

    public ExpenseCategory getCategoryById(Long id) {
        return expenseCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

}
