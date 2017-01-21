import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import { Customer } from './customer.model';

@Injectable()
export class CustomerService {

  constructor(private http: Http) { }

  public createCustomer(customer: Customer) {
    return this.http.post('http://localhost:8080/api/customer', JSON.stringify(customer)).toPromise();
  }
}
