package com.bank.repository;

import com.bank.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    private final JdbcTemplate jdbc;

    public CustomerRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Customer> mapper = (rs, rn) -> new Customer(
            rs.getInt("Customer_ID"),
            rs.getString("Name"),
            rs.getString("Address"),
            rs.getString("Phone")
    );

    public List<Customer> findAll() {
        return jdbc.query("SELECT * FROM Customer", mapper);
    }

    public Optional<Customer> findById(int id) {
        List<Customer> result = jdbc.query(
                "SELECT * FROM Customer WHERE Customer_ID = ?", mapper, id);
        return result.stream().findFirst();
    }

    public int save(Customer c) {
        return jdbc.update(
                "INSERT INTO Customer (Name, Address, Phone) VALUES (?, ?, ?)",
                c.getName(), c.getAddress(), c.getPhone());
    }

    public int update(int id, Customer c) {
        return jdbc.update(
                "UPDATE Customer SET Name = ?, Address = ?, Phone = ? WHERE Customer_ID = ?",
                c.getName(), c.getAddress(), c.getPhone(), id);
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM Customer WHERE Customer_ID = ?", id);
    }
}
