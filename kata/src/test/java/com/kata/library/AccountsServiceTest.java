package com.kata.library;

import com.kata.library.exception.BookNotFoundException;
import com.kata.library.model.Account;
import com.kata.library.model.Book;
import com.kata.library.repository.AccountsRepository;
import com.kata.library.service.AccountsService;
import com.kata.library.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountsServiceTest {
    @Mock
    private AccountsRepository accountsRepo;

    @InjectMocks
    private AccountsService accountsService;

    @Test
    void addCustomer_successfully() {
        Long accountId1 = 1L;

        Account account = new Account();
        account.setId(accountId1);
        account.setName("George");
        account.setEmail("george@gmail.com");

        Account savedAccount = new Account();
        savedAccount.setId(accountId1);
        savedAccount.setName("George");
        savedAccount.setEmail("george@gmail.com");

        when(accountsRepo.save(any(Account.class))).thenReturn(savedAccount);

        Account customer = accountsService.addCustomer(account);

        assertEquals("George", customer.getName());
        assertEquals("george@gmail.com", customer.getEmail());
        verify(accountsRepo).save(account);
    }

    @Test
    void getAllAccounts_returnsAllAccounts() {
        Long accountId1 = 1L;
        Long accountId2 = 2L;

        Account account1 = new Account();
        account1.setId(accountId1);
        account1.setName("George");
        account1.setEmail("george@gmail.com");

        Account account2 = new Account();
        account2.setId(accountId2);
        account2.setName("Dan");
        account2.setEmail("dan@gmail.com");

        when(accountsRepo.findAll()).thenReturn(List.of(account1, account2));

        List<Account> accounts = accountsService.getAllCustomers();

        assertEquals(2, accounts.size());
        assertEquals("George", accounts.get(0).getName());
        assertEquals("Dan", accounts.get(1).getName());

        verify(accountsRepo).findAll();
    }

    @Test
    void findById_returnCustomer() {
        Long accountId = 1L;

        Account account = new Account();
        account.setId(accountId);
        account.setName("George");
        account.setEmail("george@gmail.com");

        when(accountsRepo.findById(accountId)).thenReturn(Optional.of(account));

        Optional<Account> result = accountsService.findById(accountId);

        assertTrue(result.isPresent());
        assertEquals("George", result.get().getName());
    }

    @Test
    void findById_notPresent_whenNoAccountWithId() {
        Long accountId = 1L;

        Optional<Account> account = accountsService.findById(accountId);

        assertFalse(account.isPresent());
    }
}
