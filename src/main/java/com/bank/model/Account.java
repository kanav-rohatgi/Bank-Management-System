package com.bank.model;

import java.math.BigDecimal;

public class Account {
    private Integer accountNo;
    private String accountType;
    private BigDecimal balance;
    private Integer customerId;
    private Integer branchId;

    public Account() {}
    public Account(Integer accountNo, String accountType, BigDecimal balance, Integer customerId, Integer branchId) {
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.balance = balance;
        this.customerId = customerId;
        this.branchId = branchId;
    }

    public Integer getAccountNo() { return accountNo; }
    public void setAccountNo(Integer accountNo) { this.accountNo = accountNo; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public Integer getBranchId() { return branchId; }
    public void setBranchId(Integer branchId) { this.branchId = branchId; }
}
