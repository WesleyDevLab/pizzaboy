import { Component, OnInit, ViewChild } from '@angular/core';
import { CustomerComponent } from './customer.component';
import { CustomerService } from './customer.service';
import { Customer } from './customer.model';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {
  
/*  @ViewChild(CustomerComponent)
  private customerComponent: CustomerComponent;*/
  private c: Customer;

  saved: boolean = false;
  error: boolean = false;
  nocustomer: boolean = false;
  
  constructor(private customerService: CustomerService) {
    this.customerService.custObservable.subscribe(c => this.c = c);
  }

  ngOnInit() {
    this.customerService.getCustomer().then(c => {
      console.log(c);
      //this.customerComponent.customer = c;
    }).catch(c => {
      console.log("no customer data: " + c);
      this.nocustomer = true;
    });
  }

  public save() {
    //let c = this.customerComponent.customer;
    this.customerService.saveCustomer(this.c).then(() => {
        this.nocustomer = false;
        this.saved = true;
      }).catch(() => {
        this.error = true;
        this.saved = false;
        this.nocustomer = false;
      });
  }

}
