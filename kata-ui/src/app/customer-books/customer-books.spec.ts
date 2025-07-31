import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerBooks } from './customer-books';

describe('CustomerBooks', () => {
  let component: CustomerBooks;
  let fixture: ComponentFixture<CustomerBooks>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomerBooks]
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
