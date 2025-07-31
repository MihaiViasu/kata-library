import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LibraryService } from '../services/library.service';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { HttpClientModule } from '@angular/common/http';


@Component({
  selector: 'app-return-book',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule
  ],
  templateUrl: './return-book.html',
  styleUrl: './return-book.css'
})
export class ReturnBook {
  accountId: number = 0;
  bookId: number = 0;

  constructor(private libraryService: LibraryService) {}

  returnBook() {
    this.libraryService.returnBook(this.accountId, this.bookId).subscribe({
      next: (data) => {
        alert(data.message);
      }, 
      error: (err) => {
        alert('Error: ' + err.error?.error || 'Could not return book.');
      }
    });
  }
}
