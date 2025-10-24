package com.expensetracker.repository;

import com.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByCategory(String category);
    List<Expense> findByFamilyMember(String familyMember);
    List<Expense> findByDateBetween(LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double getTotalExpenses();
    
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.category = ?1")
    Double getTotalByCategory(String category);
    
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.familyMember = ?1")
    Double getTotalByFamilyMember(String familyMember);
}
