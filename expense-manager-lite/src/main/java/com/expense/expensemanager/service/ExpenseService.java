package com.expense.expensemanager.service;

import com.expense.expensemanager.model.Expense;
import com.expense.expensemanager.model.User;
import com.expense.expensemanager.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ExpenseService {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseService.class);

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense, User currentUser) {
        // Set the user who created the expense
        expense.setUser(currentUser);
        
        Expense saved = expenseRepository.save(expense);
        logger.info("Expense created - ID: {}, Amount: {}, UserId: {}", 
                saved.getId(), saved.getAmount(), currentUser.getId());
        return saved;
    }

    public Expense updateExpense(UUID id, Expense updatedExpense, User currentUser) {
        Expense existing = getExpenseById(id);
        
        // Users can only modify their own expenses
        if (!existing.getUser().getId().equals(currentUser.getId())) {
            logger.warn("Unauthorized expense update attempt - ExpenseId: {}, UserId: {}", id, currentUser.getId());
            throw new AccessDeniedException("You don't have permission to modify this expense");
        }
        
        existing.setDescription(updatedExpense.getDescription());
        existing.setAmount(updatedExpense.getAmount());
        existing.setDate(updatedExpense.getDate());
        existing.setCategory(updatedExpense.getCategory());
        
        Expense saved = expenseRepository.save(existing);
        logger.info("Expense updated - ID: {}, Amount: {}, UserId: {}", 
                saved.getId(), saved.getAmount(), currentUser.getId());
        return saved;
    }

    public List<Expense> getExpensesForUser(User user) {
        // All users can only see their own expenses
        return expenseRepository.findByUserIdOrderByDateDesc(user.getId());
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(UUID id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }

    public void deleteExpense(UUID id, User currentUser) {
        Expense expense = getExpenseById(id);
        
        // Users can only delete their own expenses
        if (!expense.getUser().getId().equals(currentUser.getId())) {
            logger.warn("Unauthorized expense deletion attempt - ExpenseId: {}, UserId: {}", id, currentUser.getId());
            throw new AccessDeniedException("You don't have permission to delete this expense");
        }
        
        logger.info("Expense deleted - ID: {}, Amount: {}, UserId: {}", 
                id, expense.getAmount(), currentUser.getId());
        expenseRepository.deleteById(id);
    }

    public double getTotalExpenseForCategory(UUID categoryId) {
        List<Expense> expenses = expenseRepository.findByCategoryId(categoryId);        
        return expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public double getTotalExpenseForUser(UUID userId, LocalDate startDate, LocalDate endDate) {
        List<Expense> expenses;
        if (startDate != null && endDate != null) {
            expenses = expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, startDate, endDate);
        } else {
            expenses = expenseRepository.findByUserIdOrderByDateDesc(userId);
        }
        return expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}

