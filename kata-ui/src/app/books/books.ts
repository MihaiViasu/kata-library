import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LibraryService } from '../services/library.service';

import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { Book } from '../models/book.model';

@Component({
  selector: 'app-books',
  templateUrl: './books.html',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatCardModule],
  styleUrl: './books.css'
})
export class Books {
  books: Book[] = [];
  displayedColumns = ['id', 'title', 'author', 'isbn', 'totalCopies', 'availableCopies'];

  constructor(private libraryService: LibraryService) {}

  ngOnInit(): void {
    this.libraryService.getAllBooks().subscribe(data => {
      this.books = data;
    });
  }
}
