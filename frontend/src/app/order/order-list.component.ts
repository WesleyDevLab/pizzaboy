import { Component, OnInit } from '@angular/core';
import { Order } from './order.model';
import { OrderService } from './order.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  orders: Order[] = [];
  noorders: boolean = false;
  error: boolean = false;

  constructor(private orderService: OrderService) {
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

}
