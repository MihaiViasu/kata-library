package com.kata.library.service;

import com.kata.library.exception.BookNotFoundException;
import com.kata.library.exception.CustomerBookNotFoundException;
import com.kata.library.exception.CustomerNotFoundException;
import com.kata.library.exception.NoAvailableCopiesException;
import com.kata.library.model.Account;
import com.kata.library.model.Book;
import com.kata.library.model.BorrowBook;
import com.kata.library.repository.BookRepository;
import com.kata.library.repository.BorrowedRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LibraryService {
    @Autowired
    private BookRepository bookRepo;
    @Autowired private BorrowedRecordRepository borrowedRepo;
    @Autowired private AccountsService accountsService;

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public void borrowBook(Long accountId, Long bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException("Cannot find book with bookId:" + bookId));
        if (book.getAvailableCopies() <= 0) throw new NoAvailableCopiesException("No copies available for this book");;

        Account account = accountsService.findById(accountId).orElseThrow(() -> new CustomerNotFoundException("Cannot find customer with accountId:" + accountId));

        BorrowBook record = new BorrowBook();
        record.setBook(book);
        record.setAccount(account);
        record.setBorrowedAt(LocalDateTime.now());
        borrowedRepo.save(record);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.save(book);
    }

    public Book returnBook(Long accountId, Long bookId) {
        BorrowBook record = borrowedRepo.findTopByAccountIdAndBookIdAndReturnedAtIsNull(accountId, bookId)
                .orElseThrow(() -> new CustomerBookNotFoundException("Cannot find the book with bookId: "+ bookId +
                                                                     " at this customer with accountId: " + accountId));

        record.setReturnedAt(LocalDateTime.now());
        borrowedRepo.save(record);

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        return bookRepo.save(book);
    }

    public List<Book> getBorrowedBooks(Long accountId) {
        return borrowedRepo.findByAccountIdAndReturnedAtIsNull(accountId)
                .stream()
                .map(BorrowBook::getBook)
                .toList();
    }
}

