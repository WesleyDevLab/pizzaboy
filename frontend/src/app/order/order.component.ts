import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { ShoppingCartService } from '../shopping-cart/shopping-cart.service';
import { OrderService } from './order.service';
import { AuthenticationService } from '../authentication/authentication.service';

import { RegisterComponent } from '../authentication/register/register.component';

import { OrderItem } from './order-item.model';
import { CartItem } from '../shopping-cart/cart-item.model';
import { Customer } from '../customer/customer.model';
import { User } from '../authentication/user.model';
import { Order } from './order.model';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
  providers: [OrderService]
})
export class OrderComponent implements OnInit {
  orders: CartItem[];
  customer: Customer;
  register: boolean = false;
  
  @ViewChild(RegisterComponent)
  registerComponent: RegisterComponent;

  tabs : any = [
    {},
    {disabled: true}
  ]

  constructor(private cartService: ShoppingCartService, private router: Router, private orderService: OrderService, private authService: AuthenticationService) {
    cartService.orders.subscribe(neworders => {
      this.orders = neworders;
    });

  }
  /*
  if register -> register then 
  create customer then
  create order
  */
  public orderNow() {
    if(this.register) {
      console.log("register == true");
      let u = new User();
      u.mail = this.registerComponent.mail;
      // TODO pw
      u.password = this.registerComponent.pw1;
      this.authService.register(u).then((user) => {
        this.authService.login(user.mail, user.password).then((user) => {

        });
        let o = new Order();
        o.customer = this.customer;
        o.customer.user = user;
        console.log(user);
        this.orders.forEach(e => {
          o.items.push(new OrderItem(e.pizza.ordernumber, e.quantity));
        });
      
        console.log(o);
        this.orderService.order(o);
      }).catch(e => {
        console.log(e);
        this.registerComponent.msgexists = "E-Mail already registerd!";
      });
    } else {
      let o = new Order();
      o.customer = this.customer;
      
      this.orders.forEach(e => {
        o.items.push(new OrderItem(e.pizza.ordernumber, e.quantity));
      });
      
      console.log(o);
      this.orderService.order(o);
    }
  }

  public get total() {
     return this.cartService.total;
  }

  public switchTabs() {
    this.tabs[1].disabled = false;
    this.tabs[0].active = !this.tabs[0].active;
    this.tabs[1].active = !this.tabs[1].active;
    console.log(this.tabs);
  }

  // redirect, if there are no orders
  ngOnInit() {
    console.log('init order component');
    if(this.orders.length == 0) {
      this.router.navigate(["/"]);
    }

    this.customer = new Customer();
    this.tabs[0].active = true;
  }

}
