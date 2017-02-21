import { Component, OnInit, ViewChild } from '@angular/core';
import { CustomerComponent } from './customer.component';
import { CustomerService } from './customer.service';
import { Customer } from './customer.model';

import { Message } from 'primeng/primeng';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {
  private c: Customer;

  msgs: Message[] = [];
  
  constructor(private customerService: CustomerService) {
    this.customerService.custObservable.subscribe(c => this.c = c);
  }

  ngOnInit() {
    this.customerService.getCustomer().then(c => {
      console.log(c);
    }).catch(c => {
      console.log("no customer data: " + c);
      this.msgs = [];
      this.msgs.push({severity: 'info', detail: 'no customer data set!'});
    });
  }

  public save() {
    this.customerService.saveCustomer(this.c).then(() => {
        this.msgs = [];
        this.msgs.push({severity: 'success', detail: 'customer data saved!'});
      }).catch(() => {
        this.msgs = [];
        this.msgs.push({severity: 'error', detail: 'something went wrong'});
      });
  }

}
