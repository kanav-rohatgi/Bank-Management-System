package com.bank.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private Integer transactionId;
    private LocalDate date;
    private BigDecimal amount;
    private String type;
    private Integer accountNo;

    public Transaction() {}
    public Transaction(Integer transactionId, LocalDate date, BigDecimal amount, String type, Integer accountNo) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.accountNo = accountNo;
    }

    public Integer getTransactionId() { return transactionId; }
    public void setTransactionId(Integer transactionId) { this.transactionId = transactionId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Integer getAccountNo() { return accountNo; }
    public void setAccountNo(Integer accountNo) { this.accountNo = accountNo; }
}
