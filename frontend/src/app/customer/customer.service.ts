import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';

import { Customer } from './customer.model';

@Injectable()
export class CustomerService {

  constructor(private ahttp: AuthHttp) { }

  public saveCustomer(customer: Customer) {
    return this.ahttp.post('http://localhost:8080/api/customer', JSON.stringify(customer)).toPromise();
  }

  public getCustomer(): Promise<Customer> {
    return this.ahttp.get("http://localhost:8080/api/customer").toPromise().then(c => {
      console.log(c);
      return c.json();
    }).catch(c => {
      console.log("no customer data: " + c)
      return Promise.reject("no customer data");
    });
  }
}
