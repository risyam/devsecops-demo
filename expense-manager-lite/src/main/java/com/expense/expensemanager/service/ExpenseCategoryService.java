package com.expense.expensemanager.service;


import com.expense.expensemanager.model.ExpenseCategory;
import com.expense.expensemanager.model.User;
import com.expense.expensemanager.repository.ExpenseCategoryRepository;
import com.expense.expensemanager.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ExpenseCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseCategoryService.class);

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;
    
    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseCategory addCategory(ExpenseCategory expenseCategory, User currentUser) {
        // Set the user who created the category
        expenseCategory.setUser(currentUser);
        ExpenseCategory saved = expenseCategoryRepository.save(expenseCategory);
        logger.info("Category created - ID: {}, Name: {}, UserId: {}", 
                saved.getId(), saved.getName(), currentUser.getId());
        return saved;
    }

    public List<ExpenseCategory> getAllCategories(User currentUser) {
        // Return only categories created by the current user
        return expenseCategoryRepository.findByUserOrderByNameAsc(currentUser);
    }

    public List<ExpenseCategory> getAllCategories() {
        // For backward compatibility or admin purposes
        return expenseCategoryRepository.findAll();
    }

    public void deleteCategory(UUID id, User currentUser) {
        ExpenseCategory category = getCategoryById(id);
        
        // Check if the category belongs to the current user
        if (!category.getUser().getId().equals(currentUser.getId())) {
            logger.warn("Unauthorized category deletion attempt - CategoryId: {}, UserId: {}", id, currentUser.getId());
            throw new IllegalStateException("You don't have permission to delete this category");
        }
        
        // Check if category is being used by any expenses
        long expenseCount = expenseRepository.countByCategoryId(id);
        if (expenseCount > 0) {
            logger.warn("Category deletion blocked - CategoryId: {}, InUseCount: {}", id, expenseCount);
            throw new IllegalStateException(
                "Cannot delete category '" + category.getName() + 
                "' because it is being used by " + expenseCount + " expense(s). " +
                "Please reassign or delete those expenses first.");
        }
        
        logger.info("Category deleted - ID: {}, Name: {}, UserId: {}", id, category.getName(), currentUser.getId());
        expenseCategoryRepository.deleteById(id);
    }

    public ExpenseCategory getCategoryById(UUID id) {
        return expenseCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

}
