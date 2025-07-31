import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { CustomerBooks } from './customer-books';

describe('CustomerBooks', () => {
  let component: CustomerBooks;
  let fixture: ComponentFixture<CustomerBooks>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomerBooks, HttpClientTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerBooks);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
