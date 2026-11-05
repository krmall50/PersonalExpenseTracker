package com.krmall50.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    private int id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private int categoryId;

    public Expense(int id, String description, BigDecimal amount, LocalDate date, int categoryId) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
    }

    public Expense(String description, BigDecimal amount, LocalDate date, int categoryId) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setId(int id) {
        if (id >= 0)
            this.id = id;
    }

    public void setdescription(String description) {
        if (!description.equals(null))
            this.description = description;
    }

    public void setDate(LocalDate date) {
        if (!date.equals(null))
            this.date = date;
    }

    public void setAmount(BigDecimal amount) {
        if (!amount.equals(null) && amount.doubleValue() > 0)
            this.amount = amount;
    }

    public void setCategoryId(int categoryId) {
        if (categoryId >= 0)
            this.categoryId = categoryId;
    }

}
