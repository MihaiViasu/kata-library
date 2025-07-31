package com.kata.library.service;

import com.kata.library.model.Account;
import com.kata.library.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountsService {
    @Autowired
    private AccountsRepository accountsRepo;

    public Account addCustomer(Account customer) {
        return accountsRepo.save(customer);
    }

    public List<Account> getAllCustomers() {
        return accountsRepo.findAll();
    }

    public Optional<Account> findById(Long accountId) {
        return accountsRepo.findById(accountId);
    }
}
