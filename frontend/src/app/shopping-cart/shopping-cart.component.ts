import { Component, OnInit } from '@angular/core';

import { ShoppingCartService } from './shopping-cart.service';

import { CartItem } from './cart-item.model';

@Component({
  selector: 'shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent {
  items: CartItem[];

  constructor(private cartService: ShoppingCartService) {
    //this.orders = cartService.getOrders();
    cartService.orders.subscribe(items => {
      this.items = items;
    });
   }

   public delete(o: CartItem) {
     console.log(o);
     this.cartService.delete(o);
   }

   public get total() {
     return this.cartService.total;
   }
}
