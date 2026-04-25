package com.bank.controller;

import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository txRepo;
    private final AccountRepository accountRepo;

    public TransactionController(TransactionRepository txRepo, AccountRepository accountRepo) {
        this.txRepo = txRepo;
        this.accountRepo = accountRepo;
    }

    @GetMapping
    public List<Transaction> getAll() {
        return txRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getById(@PathVariable int id) {
        return txRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/account/{accountNo}")
    public List<Transaction> getByAccount(@PathVariable int accountNo) {
        return txRepo.findByAccountNo(accountNo);
    }

    // Deposit / Withdrawal — updates balance automatically
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Transaction transaction) {
        var accountOpt = accountRepo.findById(transaction.getAccountNo());
        if (accountOpt.isEmpty()) return ResponseEntity.badRequest().body("Account not found");

        var account = accountOpt.get();
        BigDecimal currentBalance = account.getBalance();
        String type = transaction.getType().toUpperCase();

        if (type.equals("WITHDRAWAL")) {
            if (currentBalance.compareTo(transaction.getAmount()) < 0)
                return ResponseEntity.badRequest().body("Insufficient balance");
            accountRepo.updateBalance(account.getAccountNo(), currentBalance.subtract(transaction.getAmount()));
        } else if (type.equals("DEPOSIT")) {
            accountRepo.updateBalance(account.getAccountNo(), currentBalance.add(transaction.getAmount()));
        }

        if (transaction.getDate() == null) transaction.setDate(LocalDate.now());
        txRepo.save(transaction);
        return ResponseEntity.ok("Transaction recorded");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Transaction transaction) {
        int rows = txRepo.update(id, transaction);
        return rows > 0 ? ResponseEntity.ok("Transaction updated") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        int rows = txRepo.delete(id);
        return rows > 0 ? ResponseEntity.ok("Transaction deleted") : ResponseEntity.notFound().build();
    }
}
