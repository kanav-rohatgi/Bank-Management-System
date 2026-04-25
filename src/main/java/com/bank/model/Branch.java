package com.bank.model;

public class Branch {
    private Integer branchId;
    private String branchName;

    public Branch() {}
    public Branch(Integer branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }

    public Integer getBranchId() { return branchId; }
    public void setBranchId(Integer branchId) { this.branchId = branchId; }
    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }
}
