import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { ShoppingCartService } from '../shopping-cart/shopping-cart.service';
import { OrderService } from './order.service';
import { AuthenticationService } from '../authentication/authentication.service';
import { CustomerService } from '../customer/customer.service';

import { RegisterComponent } from '../authentication/register/register.component';
import { CustomerComponent } from '../customer/customer.component';

import { OrderItem } from './order-item.model';
import { CartItem } from '../shopping-cart/cart-item.model';
import { Customer } from '../customer/customer.model';
import { User } from '../authentication/user.model';
import { Order } from './order.model';

import { MenuItem } from 'primeng/primeng';
import { Message } from 'primeng/primeng';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent implements OnInit {
  orders: CartItem[];
  register: boolean = false;
  done: boolean = false;
  private msgs: Message[] = [];
  active: number = 0;
  private steps: MenuItem[];
  private customer: Customer;
  private u: User;

  @ViewChild(RegisterComponent)
  private registerComponent: RegisterComponent;

  constructor(private cartService: ShoppingCartService, private router: Router, private orderService: OrderService, private authService: AuthenticationService, private customerService: CustomerService) {
    cartService.orders.subscribe(neworders => {
      this.orders = neworders;
    });

    customerService.custObservable.subscribe(c => this.customer = c);
  }
  /*
  if register -> register then 
  create customer then
  create order
  */
  public orderNow() {
    if (this.register) {
      console.log("register == true");

      this.authService.register(this.u).then((user) => {
        this.authService.login(user.mail, user.password).then((user) => {
          let o = new Order();
          o.customer = this.customer;
          //o.customer.user = user;
          console.log(user);
          this.orders.forEach(e => {
            o.items.push(new OrderItem(e.pizza.ordernumber, e.quantity));
          });

          console.log(o);
          this.orderService.order(o).then(() => this.finished()).catch(() => this.onerror());
        });

      }).catch(e => {
        console.log(e);
        //this.registerComponent.msgexists = "E-Mail already registerd!";
        this.msgs.push({severity: 'error', summary: 'Error', detail: 'E-Mail already registerd!'});
      });
    } else {
      let o = new Order();
      o.customer = this.customer;

      this.orders.forEach(e => {
        o.items.push(new OrderItem(e.pizza.ordernumber, e.quantity));
      });

      console.log(o);
      this.orderService.order(o).then(() => this.finished()).catch(() => this.onerror());
    }
  }

  private finished() {
    this.msgs.push({ severity: 'success', summary: 'Order has been sent' });
    this.done = true;
    this.cartService.clean();
  }

  private onerror() {
    this.msgs.push({ severity: 'error', summary: 'Error', detail: 'something went wrong' });
  }

  public get total() {
    return this.cartService.total;
  }


  // redirect, if there are no orders
  ngOnInit() {
    console.log('init order component');
    if (this.orders.length == 0) {
      this.router.navigate(["/"]);
    }

    this.steps = [{
      label: 'review order'
    },
    {
      label: 'delivery information'
    },
    {
      label: 'confirm'
    }];

    /*    this.customerService.getCustomer().then(c => {
          console.log(c);
          this.customerComponent.customer = c;
        }).catch(() => console.log(""));*/
  }

  public loggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  public nextPage() {
    if (this.register) {
      this.u = new User();
      this.u.mail = this.registerComponent.mail;
      // TODO pw
      this.u.password = this.registerComponent.pw1;
    }
    this.active = 2;
  }

}
