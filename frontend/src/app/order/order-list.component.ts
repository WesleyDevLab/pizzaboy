import { Component, OnInit } from '@angular/core';
import { Order } from './order.model';
import { OrderService } from './order.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  orders: Order[] = [];
  noorders: boolean = false;
  error: boolean = false;

  constructor(private orderService: OrderService, private router: Router) {
    this.orderService.getOrders()
      .then(o => {
        this.orders = o;
        if(this.orders.length == 0) {
          this.noorders = true;
        }
      }).catch(() => this.error = true);
  }

  ngOnInit() {
  }

  public showOrder(o: Order) {
    console.log(o);
    this.router.navigate(['/user/order', o.id]);
  }
}
