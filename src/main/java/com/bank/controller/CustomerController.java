package com.bank.controller;

import com.bank.model.Customer;
import com.bank.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository repo;

    public CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Customer> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable int id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Customer customer) {
        int rows = repo.save(customer);
        return rows > 0 ? ResponseEntity.ok("Customer created") : ResponseEntity.badRequest().body("Failed");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Customer customer) {
        int rows = repo.update(id, customer);
        return rows > 0 ? ResponseEntity.ok("Customer updated") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        int rows = repo.delete(id);
        return rows > 0 ? ResponseEntity.ok("Customer deleted") : ResponseEntity.notFound().build();
    }
}
