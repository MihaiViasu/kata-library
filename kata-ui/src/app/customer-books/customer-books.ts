import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LibraryService } from '../services/library.service';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Book } from '../models/book.model';


@Component({
  selector: 'app-user-books',
  imports: [
    CommonModule,
    FormsModule,
    MatTableModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './customer-books.html',
  styleUrl: './customer-books.css'
})
export class CustomerBooks {
  borrowedBooks: Book[] = [];
  accountId: number = 1;
  displayedColumns = ['id', 'title', 'author'];

  constructor(private libraryService: LibraryService) {}

  getBorrowedBooks() {
    this.libraryService.getBorrowedBooks(this.accountId).subscribe({
      next: (data) => {
        this.borrowedBooks = data;
      }, 
      error: (err) => {
        alert('Error: ' + err.error?.error || 'Could not get borrowed books.');
      }
    });
  }
}
