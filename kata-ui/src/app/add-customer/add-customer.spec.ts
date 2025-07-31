import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { AddCustomer } from './add-customer';

describe('AddBook', () => {
  let component: AddCustomer;
  let fixture: ComponentFixture<AddCustomer>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddCustomer, HttpClientTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddCustomer);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
