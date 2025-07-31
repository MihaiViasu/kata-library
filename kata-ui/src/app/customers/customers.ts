import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.html',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatCardModule],
  styleUrl: './customers.css'
})
export class Customers {
  customers: any[] = [];
  displayedColumns = ['id', 'name', 'email'];

  constructor(private accountsService: AccountService) {}

  ngOnInit(): void {
    this.accountsService.getAllCustomers().subscribe(data => {
      this.customers = data;
    });
  }
}
