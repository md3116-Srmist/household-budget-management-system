package com.expensetracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
public class Expense {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    @NotNull(message = "Amount is required")
    private Double amount;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    @NotBlank(message = "Family member is required")
    private String familyMember;
    
    private LocalDateTime date;
    
    private String paymentMethod;
    
    private String notes;
    
    @PrePersist
    protected void onCreate() {
        date = LocalDateTime.now();
    }
    
    // Constructors
    public Expense() {}
    
    public Expense(String description, Double amount, String category, String familyMember) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.familyMember = familyMember;
        this.date = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getFamilyMember() { return familyMember; }
    public void setFamilyMember(String familyMember) { this.familyMember = familyMember; }
    
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
