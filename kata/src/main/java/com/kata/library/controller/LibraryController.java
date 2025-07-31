package com.kata.library.controller;

import com.kata.library.model.Book;
import com.kata.library.model.BorrowRequest;
import com.kata.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired private LibraryService libraryService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(libraryService.getAllBooks());
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(libraryService.addBook(book));
    }

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest request) {
        libraryService.borrowBook(request.getAccountId(), request.getBookId());
        return ResponseEntity.ok(new ApiResponse<>(true, "Book borrowed successfully", null));
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestBody BorrowRequest request) {
        libraryService.returnBook(request.getAccountId(), request.getBookId());
        return ResponseEntity.ok(new ApiResponse<>(true, "Book returned successfully", null));
    }

    @GetMapping("/customer/{accountId}/borrowed-books")
    public List<Book> getBorrowedBooks(@PathVariable Long accountId) {
        return libraryService.getBorrowedBooks(accountId);
    }
}

