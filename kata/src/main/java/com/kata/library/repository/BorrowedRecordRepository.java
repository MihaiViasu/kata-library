package com.kata.library.repository;

import com.kata.library.model.BorrowBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowedRecordRepository extends JpaRepository<BorrowBook, Long> {
    List<BorrowBook> findByAccountIdAndReturnedAtIsNull(Long accountId);
    Optional<BorrowBook> findTopByAccountIdAndBookIdAndReturnedAtIsNull(Long accountId, Long bookId);
}
