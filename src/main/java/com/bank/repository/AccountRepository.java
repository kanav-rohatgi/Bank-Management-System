package com.bank.repository;

import com.bank.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {

    private final JdbcTemplate jdbc;

    public AccountRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Account> mapper = (rs, rn) -> new Account(
            rs.getInt("Account_No"),
            rs.getString("Account_Type"),
            rs.getBigDecimal("Balance"),
            rs.getInt("Customer_ID"),
            rs.getInt("Branch_ID")
    );

    public List<Account> findAll() {
        return jdbc.query("SELECT * FROM Account", mapper);
    }

    public Optional<Account> findById(int id) {
        List<Account> result = jdbc.query(
                "SELECT * FROM Account WHERE Account_No = ?", mapper, id);
        return result.stream().findFirst();
    }

    public List<Account> findByCustomerId(int customerId) {
        return jdbc.query(
                "SELECT * FROM Account WHERE Customer_ID = ?", mapper, customerId);
    }

    public int save(Account a) {
        return jdbc.update(
                "INSERT INTO Account (Account_Type, Balance, Customer_ID, Branch_ID) VALUES (?, ?, ?, ?)",
                a.getAccountType(), a.getBalance(), a.getCustomerId(), a.getBranchId());
    }

    public int update(int id, Account a) {
        return jdbc.update(
                "UPDATE Account SET Account_Type = ?, Balance = ?, Customer_ID = ?, Branch_ID = ? WHERE Account_No = ?",
                a.getAccountType(), a.getBalance(), a.getCustomerId(), a.getBranchId(), id);
    }

    public int updateBalance(int accountNo, BigDecimal newBalance) {
        return jdbc.update(
                "UPDATE Account SET Balance = ? WHERE Account_No = ?",
                newBalance, accountNo);
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM Account WHERE Account_No = ?", id);
    }
}
