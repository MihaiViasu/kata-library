import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LibraryService } from '../services/library.service';
import { CommonModule } from '@angular/common';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-borrow-book',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule
  ],
  templateUrl: './borrow-book.html',
  styleUrl: './borrow-book.css'
})
export class BorrowBook {
  accountId: number = 0;
  bookId: number = 0;

  constructor(private libraryService: LibraryService) {}

  borrowBook() {
    this.libraryService.borrowBook(this.accountId, this.bookId).subscribe({
      next: (data) => {
        alert(data.message);
      }, 
      error: (err) => {
        alert('Error: ' + err.error?.error || 'Could not borrow book.');
      }
    });
  }
}
