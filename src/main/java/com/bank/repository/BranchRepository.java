package com.bank.repository;

import com.bank.model.Branch;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BranchRepository {

    private final JdbcTemplate jdbc;

    public BranchRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Branch> mapper = (rs, rn) -> new Branch(
            rs.getInt("Branch_ID"),
            rs.getString("Branch_Name")
    );

    public List<Branch> findAll() {
        return jdbc.query("SELECT * FROM Branch", mapper);
    }

    public Optional<Branch> findById(int id) {
        List<Branch> result = jdbc.query(
                "SELECT * FROM Branch WHERE Branch_ID = ?", mapper, id);
        return result.stream().findFirst();
    }

    public int save(Branch b) {
        return jdbc.update(
                "INSERT INTO Branch (Branch_Name) VALUES (?)",
                b.getBranchName());
    }

    public int update(int id, Branch b) {
        return jdbc.update(
                "UPDATE Branch SET Branch_Name = ? WHERE Branch_ID = ?",
                b.getBranchName(), id);
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM Branch WHERE Branch_ID = ?", id);
    }
}
