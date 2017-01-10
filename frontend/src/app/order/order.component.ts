import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ShoppingCartService } from '../shopping-cart/shopping-cart.service';
import { OrderService } from './order.service';

import { OrderItem } from './order-item.model';
import { CartItem } from '../shopping-cart/cart-item.model';
import { Customer } from '../customer/customer.model';
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
  // TODO check password equality, strength and encrypt it for transmission
  pw1: string;
  pw2: string;

  tabs : any = [
    {},
    {disabled: true}
  ]

  constructor(private cartService: ShoppingCartService, private router: Router, private orderService: OrderService) {
    cartService.orders.subscribe(neworders => {
      this.orders = neworders;
    });

  }

  public orderNow() {
    let o = new Order();
    o.customer = this.customer;
    
    this.orders.forEach(e => {
      o.items.push(new OrderItem(e.pizza.id, e.quantity));
    });
    
    o.register = this.register;
    console.log(o);
    this.orderService.order(o);
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
