package com.kata.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
}
