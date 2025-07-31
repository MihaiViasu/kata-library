package com.kata.library;

import com.kata.library.exception.BookNotFoundException;
import com.kata.library.exception.CustomerBookNotFoundException;
import com.kata.library.exception.CustomerNotFoundException;
import com.kata.library.exception.NoAvailableCopiesException;
import com.kata.library.model.Account;
import com.kata.library.model.Book;
import com.kata.library.model.BorrowBook;
import com.kata.library.repository.BookRepository;
import com.kata.library.repository.BorrowedRecordRepository;
import com.kata.library.service.AccountsService;
import com.kata.library.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

    @Mock
    private BookRepository bookRepo;

    @Mock
    private BorrowedRecordRepository borrowedRepo;

    @Mock
    private AccountsService accountsService;

    @InjectMocks
    private LibraryService libraryService;

    @Test
    void addBook_successfully() {
        Book book = new Book();
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setIsbn("9780132350884");
        book.setTotalCopies(5);
        book.setAvailableCopies(5);

        Book savedBook = new Book();
        savedBook.setId(1L);
        savedBook.setTitle("Clean Code");
        savedBook.setAuthor("Robert C. Martin");
        savedBook.setIsbn("9780132350884");
        savedBook.setTotalCopies(5);
        savedBook.setAvailableCopies(5);

        when(bookRepo.save(any(Book.class))).thenReturn(savedBook);

        Book result = libraryService.addBook(book);

        assertEquals(5, result.getAvailableCopies());
        assertEquals("Clean Code", result.getTitle());
        verify(bookRepo).save(book);
    }

    @Test
    void borrowBook_successfully() {
        Long accountId = 1L;
        Long bookId = 2L;

        Book book = new Book();
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setIsbn("9780132350884");
        book.setId(bookId);
        book.setAvailableCopies(3);

        Account account = new Account();
        account.setId(accountId);
        account.setName("John Doe");

        when(bookRepo.findById(bookId)).thenReturn(Optional.of(book));
        when(borrowedRepo.save(any())).thenAnswer(i -> i.getArguments()[0]);
        when(accountsService.findById(accountId)).thenReturn(Optional.of(account));

        libraryService.borrowBook(accountId, bookId);

        assertEquals(2, book.getAvailableCopies());
        verify(borrowedRepo).save(any(BorrowBook.class));
        verify(bookRepo).save(book);
    }


    @Test
    void borrowBook_throwsNoAvailableCopiesException_whenNoCopiesLeft() {
        Long accountId = 1L;
        Long bookId = 2L;

        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setIsbn("9780132350884");
        book.setTitle("Clean Code");
        book.setAvailableCopies(0);

        when(bookRepo.findById(bookId)).thenReturn(Optional.of(book));

        assertThrows(NoAvailableCopiesException.class, () -> {
            libraryService.borrowBook(accountId, bookId);
        });

        verify(borrowedRepo, never()).save(any());
        verify(bookRepo, never()).save(any());
    }

    @Test
    void borrowBook_throwsBookNotFoundException_whenNoBookFound() {
        Long accountId = 1L;
        Long bookId = 2L;

        assertThrows(BookNotFoundException.class, () -> {
            libraryService.borrowBook(accountId, bookId);
        });

        verify(borrowedRepo, never()).save(any());
        verify(bookRepo, never()).save(any());
    }

    @Test
    void borrowBook_throwsCustomerNotFoundException_whenCustomerNotPresent() {
        Long accountId = 1L;
        Long bookId = 2L;

        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Clean Code");
        book.setAvailableCopies(1);

        when(bookRepo.findById(bookId)).thenReturn(Optional.of(book));

        assertThrows(CustomerNotFoundException.class, () -> {
            libraryService.borrowBook(accountId, bookId);
        });

        verify(borrowedRepo, never()).save(any());
        verify(bookRepo, never()).save(any());
    }

    @Test
    void returnBook_successfully() {
        Long accountId = 1L;
        Long bookId = 2L;

        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Clean Code");
        book.setAvailableCopies(1);

        Account account = new Account();
        account.setId(accountId);
        account.setName("George");

        BorrowBook borrowBook = new BorrowBook();
        borrowBook.setId(100L);
        borrowBook.setBook(book);
        borrowBook.setAccount(account);
        borrowBook.setReturnedAt(LocalDateTime.now());

        when(borrowedRepo.findTopByAccountIdAndBookIdAndReturnedAtIsNull(accountId, bookId))
                .thenReturn(Optional.of(borrowBook));
        when(bookRepo.save(any(Book.class))).thenReturn(book);

        libraryService.returnBook(accountId, bookId);

        assertEquals(2, book.getAvailableCopies());
        verify(borrowedRepo).save(borrowBook);
        verify(bookRepo).save(book);
    }

    @Test
    void returnBook_throwsCustomerBookNotFoundException_whenBookNotBorrowed() {
        Long accountId = 1L;
        Long bookId = 2L;

        when(borrowedRepo.findTopByAccountIdAndBookIdAndReturnedAtIsNull(accountId, bookId))
                .thenReturn(Optional.empty());

        assertThrows(CustomerBookNotFoundException.class, () -> {
            libraryService.returnBook(accountId, bookId);
        });

        verify(borrowedRepo, never()).save(any());
        verify(bookRepo, never()).save(any());
    }

    @Test
    void getBorrowedBooks_returnsBooksForAccount() {
        Long accountId = 1L;

        Book book1 = new Book();
        book1.setTitle("Clean Code");

        Book book2 = new Book();
        book2.setTitle("The Very Hungry Caterpillar");

        BorrowBook b1 = new BorrowBook();
        b1.setBook(book1);
        b1.setReturnedAt(LocalDateTime.now());

        BorrowBook b2 = new BorrowBook();
        b2.setBook(book2);
        b2.setReturnedAt(LocalDateTime.now());

        when(borrowedRepo.findByAccountIdAndReturnedAtIsNull(accountId))
                .thenReturn(List.of(b1, b2));

        List<Book> borrowedBooks = libraryService.getBorrowedBooks(accountId);

        assertEquals(2, borrowedBooks.size());
        assertEquals("Clean Code", borrowedBooks.get(0).getTitle());
        assertEquals("The Very Hungry Caterpillar", borrowedBooks.get(1).getTitle());

        verify(borrowedRepo).findByAccountIdAndReturnedAtIsNull(accountId);
    }

    @Test
    void getAllBooks_returnsAllBooks() {
        Book book1 = new Book();
        book1.setTitle("Clean Code");

        Book book2 = new Book();
        book2.setTitle("The Very Hungry Caterpillar");

        when(bookRepo.findAll()).thenReturn(List.of(book1, book2));

        List<Book> books = libraryService.getAllBooks();

        assertEquals(2, books.size());
        assertEquals("Clean Code", books.get(0).getTitle());
        assertEquals("The Very Hungry Caterpillar", books.get(1).getTitle());

        verify(bookRepo).findAll();
    }
}

