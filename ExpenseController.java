package com.expensetracker.controller;

import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://localhost:3000")
public class ExpenseController {
    
    @Autowired
    private ExpenseService service;
    
    @GetMapping
    public List<Expense> getAllExpenses() {
        return service.getAllExpenses();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Expense expense = service.getExpenseById(id);
        return expense != null ? ResponseEntity.ok(expense) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public Expense createExpense(@Valid @RequestBody Expense expense) {
        return service.createExpense(expense);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @Valid @RequestBody Expense expense) {
        Expense updated = service.updateExpense(id, expense);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        service.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/statistics")
    public Map<String, Object> getStatistics() {
        return service.getStatistics();
    }
    
    @GetMapping("/breakdown/category")
    public Map<String, Double> getCategoryBreakdown() {
        return service.getCategoryBreakdown();
    }
    
    @GetMapping("/breakdown/member")
    public Map<String, Double> getFamilyMemberBreakdown() {
        return service.getFamilyMemberBreakdown();
    }
}
