package com.krmall50.model;

import java.math.BigDecimal;

public class Expense {
    private BigDecimal amount;
    private Category category;

    public BigDecimal getAmount() {
        return amount;
    }

    public Category getCategort() {
        return category;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCategort(Category category) {
        this.category = category;
    }

}
