package com.kata.library.repository;

import com.kata.library.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Account, Long> {}
