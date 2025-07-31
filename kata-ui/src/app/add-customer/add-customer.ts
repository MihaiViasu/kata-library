import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LibraryService } from '../services/library.service';
import { CommonModule } from '@angular/common';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { AccountService } from '../services/account.service';


@Component({
  selector: 'app-add-customer',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  templateUrl: './add-customer.html',
  styleUrl: './add-customer.css'
})

export class AddCustomer {

  customer = {
    name: '',
    email: '',
  };

  constructor(private accountsService: AccountService) {}

  addCustomer() {
    this.accountsService.addAccount(this.customer).subscribe(() => {
      alert('Customer added!');
    });
  }

}
