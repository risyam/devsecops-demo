package com.expense.expensemanager.controller;

import com.expense.expensemanager.model.Expense;
import com.expense.expensemanager.model.ExpenseCategory;
import com.expense.expensemanager.service.ExpenseCategoryService;
import com.expense.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExpenseUIController {

    @Autowired
    private ExpenseCategoryService categoryService;

    @Autowired
    private ExpenseService expenseService;

    // Home Page
    @GetMapping("/")
    public String home(Model model) {
        Map<String, Double> expenseData = getExpenseDataByCategory();
        model.addAttribute("expenseData", expenseData);
        return "index";
    }

    // Add Expense Category
    @GetMapping("/add-category")
    public String showAddCategoryPage(Model model) {
        ExpenseCategory category = new ExpenseCategory();
        model.addAttribute("category", category);
        return "add-category";
    }

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute("category") ExpenseCategory category, BindingResult result) {
        if (result.hasErrors()) {
            return "add-category"; // Return the form with errors
        }
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    // View All Categories
    @GetMapping("/categories")
    public String listCategories(Model model) {
        List<ExpenseCategory> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    // Add Expense
    @GetMapping("/add-expense")
    public String showAddExpensePage(Model model) {
        Expense expense = new Expense();
        model.addAttribute("expense", expense);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "add-expense";
    }

    @PostMapping("/add-expense")
    public String addExpense(@ModelAttribute Expense expense) {
        // Retrieve category by ID from the repository
        ExpenseCategory category = categoryService.getCategoryById(expense.getCategory().getId());
        expense.setCategory(category); // Set the category object on the expense
        expenseService.addExpense(expense);
        return "redirect:/expenses";
    }

    // View All Expenses
    @GetMapping("/expenses")
    public String listExpenses(Model model) {
        List<Expense> expenses = expenseService.getAllExpenses();
        model.addAttribute("expenses", expenses);
        return "expenses";
    }

    // Delete Category
    @PostMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    // Delete Expense
    @PostMapping("/delete-expense/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }

    @GetMapping("/expense-data")
    @ResponseBody
    public Map<String, Double> getExpenseDataByCategory() {
        Map<String, Double> categoryExpenses = new HashMap<>();
        List<ExpenseCategory> categories = categoryService.getAllCategories();
        double totalExpenses = 0;
        // Calculate total expenses
        for (ExpenseCategory category : categories) {
            totalExpenses += expenseService.getTotalExpenseForCategory(category.getId());
        }
        // Calculate percentage for each category
        for (ExpenseCategory category : categories) {
            double categoryTotal = expenseService.getTotalExpenseForCategory(category.getId());
            double percentage = (totalExpenses > 0) ? (categoryTotal / totalExpenses) * 100 : 0;
            categoryExpenses.put(category.getName(), percentage);
        }
        return categoryExpenses;
    }
}
