package com.kata.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity @Getter @Setter
public class BorrowBook {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Book book;

    private LocalDateTime borrowedAt;
    private LocalDateTime returnedAt;
}

