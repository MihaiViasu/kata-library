import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const BASE_URL = 'http://localhost:8080/accounts';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) {}

  getAllCustomers(): Observable<any> {
    return this.http.get(`${BASE_URL}/customers`);
  }

  addAccount(account: any): Observable<any> {
    return this.http.post(`${BASE_URL}/customers`, account);
  }
}