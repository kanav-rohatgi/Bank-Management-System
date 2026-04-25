package com.bank.controller;

import com.bank.model.Branch;
import com.bank.repository.BranchRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {

    private final BranchRepository repo;

    public BranchController(BranchRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Branch> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getById(@PathVariable int id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Branch branch) {
        int rows = repo.save(branch);
        return rows > 0 ? ResponseEntity.ok("Branch created") : ResponseEntity.badRequest().body("Failed");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Branch branch) {
        int rows = repo.update(id, branch);
        return rows > 0 ? ResponseEntity.ok("Branch updated") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        int rows = repo.delete(id);
        return rows > 0 ? ResponseEntity.ok("Branch deleted") : ResponseEntity.notFound().build();
    }
}
