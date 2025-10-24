package com.expensetracker.service;

import com.expensetracker.model.Expense;
import com.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ExpenseService {
    
    @Autowired
    private ExpenseRepository repository;
    
    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }
    
    public Expense getExpenseById(Long id) {
        return repository.findById(id).orElse(null);
    }
    
    public Expense createExpense(Expense expense) {
        return repository.save(expense);
    }
    
    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense expense = repository.findById(id).orElse(null);
        if (expense != null) {
            expense.setDescription(expenseDetails.getDescription());
            expense.setAmount(expenseDetails.getAmount());
            expense.setCategory(expenseDetails.getCategory());
            expense.setFamilyMember(expenseDetails.getFamilyMember());
            expense.setPaymentMethod(expenseDetails.getPaymentMethod());
            expense.setNotes(expenseDetails.getNotes());
            return repository.save(expense);
        }
        return null;
    }
    
    public void deleteExpense(Long id) {
        repository.deleteById(id);
    }
    
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        Double total = repository.getTotalExpenses();
        stats.put("totalExpenses", total != null ? total : 0.0);
        stats.put("expenseCount", repository.count());
        return stats;
    }
    
    public Map<String, Double> getCategoryBreakdown() {
        Map<String, Double> breakdown = new HashMap<>();
        List<Expense> expenses = repository.findAll();
        for (Expense expense : expenses) {
            breakdown.merge(expense.getCategory(), expense.getAmount(), Double::sum);
        }
        return breakdown;
    }
    
    public Map<String, Double> getFamilyMemberBreakdown() {
        Map<String, Double> breakdown = new HashMap<>();
        List<Expense> expenses = repository.findAll();
        for (Expense expense : expenses) {
            breakdown.merge(expense.getFamilyMember(), expense.getAmount(), Double::sum);
        }
        return breakdown;
    }
}
