import { NgModule } from '@angular/core';
import { bootstrapApplication, BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { Books } from './books/books';
import { Customers } from './customers/customers';
import { AddBook } from './add-book/add-book';
import { AddCustomer } from './add-customer/add-customer';
import { BorrowBook } from './borrow-book/borrow-book';
import { ReturnBook } from './return-book/return-book';
import { CustomerBooks } from './customer-books/customer-books';
import { AppRoutingModule } from './app.routes';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    Books,
    Customers,
    AddBook,
    AddCustomer,
    BorrowBook,
    ReturnBook,
    CustomerBooks
  ],
  providers: [],
  bootstrap: []
})

export class AppModule { }
