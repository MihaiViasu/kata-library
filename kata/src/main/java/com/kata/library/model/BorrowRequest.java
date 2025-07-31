package com.kata.library.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BorrowRequest {
    private Long accountId;
    private Long bookId;
}
