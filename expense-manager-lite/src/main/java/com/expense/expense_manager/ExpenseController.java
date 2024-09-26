package com.expense.expense_manager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    @GetMapping("/hello")
    public String sayHello() {
       

            return "Hello, World!";
        
        
    }
}
