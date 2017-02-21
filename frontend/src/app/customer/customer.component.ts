import { Component, OnInit } from '@angular/core';
import { Customer } from './customer.model';
import { CustomerService } from './customer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  customer: Customer;

  constructor(private customerService: CustomerService) {
    console.log("ctr");
    
  }

  ngOnInit() {
    //this.customer = new Customer();
    console.log("ngOnInit");
    this.customerService.custObservable.subscribe(c => this.customer = c);
  }

}
