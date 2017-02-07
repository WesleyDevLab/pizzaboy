import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import { Pizza } from '../pizza/pizza.model';
import { CartItem } from './cart-item.model';

@Injectable()
export class ShoppingCartService {
  private _orders: BehaviorSubject<CartItem[]>;
  private dataStore: {
    items: CartItem[]
  };

  constructor() {
    this.dataStore = { items: [] };
    this._orders = new BehaviorSubject<CartItem[]>(this.dataStore.items);
    
  }

  public get orders() {
    console.log("getOrders");
    return this._orders.asObservable();
  }

  public get total() {
    let t = 0;
    this.dataStore.items.forEach((o, i) => {
      t += o.pizza.price * o.quantity;
    });
    return t;
  }

  public addToCart(pizza: Pizza, quantity: number) {
    // merge orders for same pizzas, add quantity
    let found = false;
    for(let o of this.dataStore.items) {
      if(o.pizza.name == pizza.name) {
        let p = o.quantity + quantity;
        o.quantity = p;
        found = true;
      }
    }

    if(!found)
      this.dataStore.items.push(new CartItem(pizza, quantity));

    this._orders.next(Object.assign({}, this.dataStore).items);
  }

  public delete(order: CartItem) {
    console.log(order);

    this.dataStore.items.forEach((o, i) => {
      if(o.pizza.name == order.pizza.name) {
        this.dataStore.items.splice(i, 1);
      }
    });

    this._orders.next(Object.assign({}, this.dataStore).items);
  }

  public clean() {
    this.dataStore.items = [];
    this._orders.next(Object.assign({}, this.dataStore).items);
  }
}
