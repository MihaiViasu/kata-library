import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Books } from './books/books';
import { Customers } from './customers/customers';
import { AddBook } from './add-book/add-book';
import { AddCustomer } from './add-customer/add-customer';
import { BorrowBook } from './borrow-book/borrow-book';
import { ReturnBook } from './return-book/return-book';
import { CustomerBooks } from './customer-books/customer-books';

export const routes: Routes = [
  { path: 'books', component: Books },
  { path: 'customers', component: Customers },
  { path: 'add-book', component: AddBook },
  { path: 'add-customer', component: AddCustomer },
  { path: 'borrow', component: BorrowBook },
  { path: 'return', component: ReturnBook },
  { path: 'user-books', component: CustomerBooks },
  { path: '', redirectTo: '/books', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}