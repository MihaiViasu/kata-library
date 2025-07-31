import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LibraryService } from '../services/library.service';
import { CommonModule } from '@angular/common';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { Book } from '../models/book.model';

@Component({
  selector: 'app-add-book',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  templateUrl: './add-book.html',
  styleUrl: './add-book.css'
})

export class AddBook {

  book = {
    title: '',
    author: '',
    isbn: '',
    totalCopies: 1,
    availableCopies: 1
  };

  constructor(private libraryService: LibraryService) {}

  addBook() {
    this.libraryService.addBook(this.book).subscribe(() => {
      alert('Book added!');
    });
  }

}
