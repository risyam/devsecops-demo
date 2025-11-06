package com.expense.expensemanager.controller;

import com.expense.expensemanager.model.Expense;
import com.expense.expensemanager.model.ExpenseCategory;
import com.expense.expensemanager.model.User;
import com.expense.expensemanager.security.SecurityUtils;
import com.expense.expensemanager.service.ExpenseCategoryService;
import com.expense.expensemanager.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ExpenseUIController {

    @Autowired
    private ExpenseCategoryService categoryService;

    @Autowired
    private ExpenseService expenseService;

    // Home/Login Page
    @GetMapping("/")
    public String home(Model model) {
        User currentUser = SecurityUtils.getCurrentUser();
        if (currentUser == null) {
            return "login";
        }
        
        // All users go to home dashboard
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Home Dashboard
    @GetMapping("/home")
    public String dashboard(Model model) {
        User currentUser = SecurityUtils.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }
        
        // Get user's expenses
        List<Expense> userExpenses = expenseService.getExpensesForUser(currentUser);
        
        // Calculate total expenses
        double totalExpenses = userExpenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
        
        // Get recent expenses (last 5)
        List<Expense> recentExpenses = userExpenses.stream()
                .limit(5)
                .collect(java.util.stream.Collectors.toList());
        
        // Get category count for current user
        long categoryCount = categoryService.getAllCategories(currentUser).size();
        
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("totalExpenses", totalExpenses);
        model.addAttribute("expenseCount", userExpenses.size());
        model.addAttribute("categoryCount", categoryCount);
        model.addAttribute("recentExpenses", recentExpenses);
        
        return "home";
    }

    // Add Expense Category
    @GetMapping("/add-category")
    public String showAddCategoryPage(Model model) {
        ExpenseCategory category = new ExpenseCategory();
        model.addAttribute("category", category);
        model.addAttribute("currentUser", SecurityUtils.getCurrentUser());
        return "add-category";
    }

    @PostMapping("/add-category")
    public String addCategory(@Valid @ModelAttribute("category") ExpenseCategory category, 
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "add-category";
        }
        
        try {
            User currentUser = SecurityUtils.getCurrentUser();
            categoryService.addCategory(category, currentUser);
            redirectAttributes.addFlashAttribute("message", "Category added successfully!");
            return "redirect:/categories";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding category: " + e.getMessage());
            return "redirect:/add-category";
        }
    }

    // View All Categories
    @GetMapping("/categories")
    public String listCategories(Model model) {
        User currentUser = SecurityUtils.getCurrentUser();
        List<ExpenseCategory> categories = categoryService.getAllCategories(currentUser);
        model.addAttribute("categories", categories);
        model.addAttribute("currentUser", currentUser);
        return "categories";
    }

    // Add Expense
    @GetMapping("/add-expense")
    public String showAddExpensePage(Model model) {
        User currentUser = SecurityUtils.getCurrentUser();
        Expense expense = new Expense();
        model.addAttribute("expense", expense);
        model.addAttribute("categories", categoryService.getAllCategories(currentUser));
        model.addAttribute("currentUser", currentUser);
        return "add-expense";
    }

    @PostMapping("/add-expense")
    public String addExpense(@Valid @ModelAttribute Expense expense, 
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        if (result.hasErrors()) {
            User currentUser = SecurityUtils.getCurrentUser();
            model.addAttribute("categories", categoryService.getAllCategories(currentUser));
            model.addAttribute("currentUser", currentUser);
            return "add-expense";
        }
        
        try {
            User currentUser = SecurityUtils.getCurrentUser();
            ExpenseCategory category = categoryService.getCategoryById(expense.getCategory().getId());
            expense.setCategory(category);
            expenseService.addExpense(expense, currentUser);
            redirectAttributes.addFlashAttribute("message", "Expense added successfully!");
            return "redirect:/expenses";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding expense: " + e.getMessage());
            return "redirect:/add-expense";
        }
    }

    // View Expenses
    @GetMapping("/expenses")
    public String listExpenses(Model model) {
        User currentUser = SecurityUtils.getCurrentUser();
        List<Expense> expenses = expenseService.getExpensesForUser(currentUser);
        model.addAttribute("expenses", expenses);
        model.addAttribute("currentUser", currentUser);
        return "expenses";
    }

    // Edit Category
    @GetMapping("/edit-category/{id}")
    public String showEditCategoryPage(@PathVariable UUID id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User currentUser = SecurityUtils.getCurrentUser();
            ExpenseCategory category = categoryService.getCategoryById(id);
            
            // Check if user owns this category
            if (!category.getUser().getId().equals(currentUser.getId())) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to edit this category.");
                return "redirect:/categories";
            }
            
            model.addAttribute("category", category);
            model.addAttribute("currentUser", currentUser);
            return "edit-category";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Category not found.");
            return "redirect:/categories";
        }
    }

    @PostMapping("/edit-category/{id}")
    public String updateCategory(@PathVariable UUID id, 
                                @Valid @ModelAttribute("category") ExpenseCategory category,
                                BindingResult result,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        if (result.hasErrors()) {
            User currentUser = SecurityUtils.getCurrentUser();
            model.addAttribute("currentUser", currentUser);
            return "edit-category";
        }
        
        try {
            User currentUser = SecurityUtils.getCurrentUser();
            ExpenseCategory existingCategory = categoryService.getCategoryById(id);
            
            // Check if user owns this category
            if (!existingCategory.getUser().getId().equals(currentUser.getId())) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to edit this category.");
                return "redirect:/categories";
            }
            
            existingCategory.setName(category.getName());
            categoryService.addCategory(existingCategory, currentUser); // This will update it
            redirectAttributes.addFlashAttribute("message", "Category updated successfully!");
            return "redirect:/categories";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating category: " + e.getMessage());
            return "redirect:/edit-category/" + id;
        }
    }

    // Delete Category
    @PostMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            User currentUser = SecurityUtils.getCurrentUser();
            categoryService.deleteCategory(id, currentUser);
            redirectAttributes.addFlashAttribute("message", "Category deleted successfully!");
        } catch (IllegalStateException e) {
            // Handle specific case where category is in use
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting category: " + e.getMessage());
        }
        return "redirect:/categories";
    }

    // Edit Expense
    @GetMapping("/edit-expense/{id}")
    public String showEditExpensePage(@PathVariable UUID id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User currentUser = SecurityUtils.getCurrentUser();
            Expense expense = expenseService.getExpenseById(id);
            
            // Check if user owns this expense
            if (!expense.getUser().getId().equals(currentUser.getId())) {
                redirectAttributes.addFlashAttribute("error", "You don't have permission to edit this expense.");
                return "redirect:/expenses";
            }
            
            model.addAttribute("expense", expense);
            model.addAttribute("categories", categoryService.getAllCategories(currentUser));
            model.addAttribute("currentUser", currentUser);
            return "edit-expense";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Expense not found.");
            return "redirect:/expenses";
        }
    }

    @PostMapping("/edit-expense/{id}")
    public String updateExpense(@PathVariable UUID id,
                               @Valid @ModelAttribute Expense expense,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (result.hasErrors()) {
            User currentUser = SecurityUtils.getCurrentUser();
            model.addAttribute("categories", categoryService.getAllCategories(currentUser));
            model.addAttribute("currentUser", currentUser);
            return "edit-expense";
        }
        
        try {
            User currentUser = SecurityUtils.getCurrentUser();
            ExpenseCategory category = categoryService.getCategoryById(expense.getCategory().getId());
            expense.setCategory(category);
            expenseService.updateExpense(id, expense, currentUser);
            redirectAttributes.addFlashAttribute("message", "Expense updated successfully!");
            return "redirect:/expenses";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating expense: " + e.getMessage());
            return "redirect:/edit-expense/" + id;
        }
    }

    // Delete Expense
    @PostMapping("/delete-expense/{id}")
    public String deleteExpense(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            User currentUser = SecurityUtils.getCurrentUser();
            expenseService.deleteExpense(id, currentUser);
            redirectAttributes.addFlashAttribute("message", "Expense deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting expense: " + e.getMessage());
        }
        return "redirect:/expenses";
    }

    @GetMapping("/expense-data")
    @ResponseBody
    public Map<String, Double> getExpenseDataByCategory() {
        User currentUser = SecurityUtils.getCurrentUser();
        Map<String, Double> categoryExpenses = new HashMap<>();
        List<ExpenseCategory> categories = categoryService.getAllCategories(currentUser);
        
        // Get user's own expenses
        List<Expense> userExpenses = expenseService.getExpensesForUser(currentUser);
        
        double totalExpenses = 0;
        // Calculate total expenses
        for (ExpenseCategory category : categories) {
            double categoryTotal = userExpenses.stream()
                    .filter(e -> e.getCategory() != null && 
                               e.getCategory().getId().equals(category.getId()))
                    .mapToDouble(Expense::getAmount)
                    .sum();
            if (categoryTotal > 0) {
                totalExpenses += categoryTotal;
            }
        }
        
        // Calculate percentage for each category
        for (ExpenseCategory category : categories) {
            double categoryTotal = userExpenses.stream()
                    .filter(e -> e.getCategory() != null && 
                               e.getCategory().getId().equals(category.getId()))
                    .mapToDouble(Expense::getAmount)
                    .sum();
            double percentage = (totalExpenses > 0) ? (categoryTotal / totalExpenses) * 100 : 0;
            if (percentage > 0) {
                categoryExpenses.put(category.getName(), percentage);
            }
        }
        return categoryExpenses;
    }
}
