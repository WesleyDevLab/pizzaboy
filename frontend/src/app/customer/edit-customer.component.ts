import { Component, OnInit, ViewChild } from '@angular/core';
import { CustomerComponent } from './customer.component';
import { AuthHttp } from 'angular2-jwt';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {
  
  @ViewChild(CustomerComponent)
  private customerComponent: CustomerComponent;
  
  constructor(private ahttp: AuthHttp) { }

  ngOnInit() {
    this.ahttp.get("http://localhost:8080/api/customer").toPromise().then(c => {
      console.log(c);
    }).catch(c => console.log("no customer data: " + c));
  }

}
