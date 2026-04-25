package com.bank.controller;

import com.bank.model.Account;
import com.bank.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository repo;

    public AccountController(AccountRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Account> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable int id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public List<Account> getByCustomer(@PathVariable int customerId) {
        return repo.findByCustomerId(customerId);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Account account) {
        int rows = repo.save(account);
        return rows > 0 ? ResponseEntity.ok("Account created") : ResponseEntity.badRequest().body("Failed");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Account account) {
        int rows = repo.update(id, account);
        return rows > 0 ? ResponseEntity.ok("Account updated") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        int rows = repo.delete(id);
        return rows > 0 ? ResponseEntity.ok("Account deleted") : ResponseEntity.notFound().build();
    }
}
