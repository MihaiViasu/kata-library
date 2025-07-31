package com.kata.library.controller;

import com.kata.library.model.Account;
import com.kata.library.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
    @Autowired
    private AccountsService accountsService;

    @GetMapping("/customers")
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountsService.getAllCustomers());
    }

    @PostMapping("/customers")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountsService.addCustomer(account));
    }
}
