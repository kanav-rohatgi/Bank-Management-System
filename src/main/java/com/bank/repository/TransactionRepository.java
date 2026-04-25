package com.bank.repository;

import com.bank.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TransactionRepository {

    private final JdbcTemplate jdbc;

    public TransactionRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Transaction> mapper = (rs, rn) -> new Transaction(
            rs.getInt("Transaction_ID"),
            rs.getDate("Date").toLocalDate(),
            rs.getBigDecimal("Amount"),
            rs.getString("Type"),
            rs.getInt("Account_No")
    );

    public List<Transaction> findAll() {
        return jdbc.query("SELECT * FROM Transaction_Table", mapper);
    }

    public Optional<Transaction> findById(int id) {
        List<Transaction> result = jdbc.query(
                "SELECT * FROM Transaction_Table WHERE Transaction_ID = ?", mapper, id);
        return result.stream().findFirst();
    }

    public List<Transaction> findByAccountNo(int accountNo) {
        return jdbc.query(
                "SELECT * FROM Transaction_Table WHERE Account_No = ?", mapper, accountNo);
    }

    public int save(Transaction t) {
        return jdbc.update(
                "INSERT INTO Transaction_Table (Date, Amount, Type, Account_No) VALUES (?, ?, ?, ?)",
                t.getDate(), t.getAmount(), t.getType(), t.getAccountNo());
    }

    public int update(int id, Transaction t) {
        return jdbc.update(
                "UPDATE Transaction_Table SET Date = ?, Amount = ?, Type = ?, Account_No = ? WHERE Transaction_ID = ?",
                t.getDate(), t.getAmount(), t.getType(), t.getAccountNo(), id);
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM Transaction_Table WHERE Transaction_ID = ?", id);
    }
}
