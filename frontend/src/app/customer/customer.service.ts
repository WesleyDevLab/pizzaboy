import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';
import { Customer } from './customer.model';

@Injectable()
export class CustomerService {
  private customer: Customer;
  private _customer: BehaviorSubject<Customer>;
  public custObservable: Observable<Customer>;

  constructor(private ahttp: AuthHttp) {
    this.customer = new Customer();
    this._customer = new BehaviorSubject<Customer>(this.customer);
    this.custObservable = this._customer.asObservable();
  }

  public saveCustomer(customer: Customer) {
    return this.ahttp.post('http://localhost:8080/api/customer', JSON.stringify(customer)).toPromise().then(() => this._customer.next(customer));
  }

  public getCustomer(): Promise<Customer> {
    return this.ahttp.get("http://localhost:8080/api/customer").toPromise().then(c => {
      console.log(c);
      this._customer.next(c.json());
      return c.json();
    }).catch(c => {
      console.log("no customer data: " + c)
      return Promise.reject("no customer data");
    });
  }
}
