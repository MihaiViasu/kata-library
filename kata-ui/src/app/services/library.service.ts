import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const BASE_URL = 'http://localhost:8080/library';

@Injectable({
  providedIn: 'root'
})
export class LibraryService {

  constructor(private http: HttpClient) {}

  getAllBooks(): Observable<any> {
    return this.http.get(`${BASE_URL}/books`);
  }

  addBook(book: any): Observable<any> {
    return this.http.post(`${BASE_URL}/books`, book);
  }

  borrowBook(accountId: number, bookId: number): Observable<any> {
    return this.http.post(`${BASE_URL}/borrow`, { accountId, bookId });
  }

  returnBook(accountId: number, bookId: number): Observable<any> {
    return this.http.post(`${BASE_URL}/return`, { accountId, bookId });
  }

  getBorrowedBooks(accountId: number): Observable<any> {
    return this.http.get(`${BASE_URL}/customer/${accountId}/borrowed-books`);
  }
}
