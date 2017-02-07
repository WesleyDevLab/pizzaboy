import { Component, OnInit, ViewChild } from '@angular/core';
import { CustomerComponent } from './customer.component';
import { CustomerService } from './customer.service';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {
  
  @ViewChild(CustomerComponent)
  private customerComponent: CustomerComponent;

  saved: boolean = false;
  error: boolean = false;
  nocustomer: boolean = false;
  
  constructor(private customerService: CustomerService) { }

  ngOnInit() {
    this.customerService.getCustomer().then(c => {
      console.log(c);
      this.customerComponent.customer = c;
    }).catch(c => {
      console.log("no customer data: " + c);
      this.nocustomer = true;
    });
  }

  public save() {
    let c = this.customerComponent.customer;
    this.customerService.saveCustomer(c).then(() => {
        this.nocustomer = false;
        this.saved = true;
      }).catch(() => {
        this.error = true;
        this.saved = false;
        this.nocustomer = false;
      });
  }

}
