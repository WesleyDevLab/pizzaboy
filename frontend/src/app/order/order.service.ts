import { Injectable } from '@angular/core';
import { Response, Headers, RequestOptions } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';

import { Order } from './order.model';

@Injectable()
export class OrderService {


  constructor(private ahttp: AuthHttp) { }

  public order(o: Order): Promise<void> {
    console.log(o);

    return this.ahttp.post('http://localhost:8080/api/order', o).toPromise()
      .then(r => {
        console.log(r);
      }).catch(r => {
        console.log(r);
        return Promise.reject(r);
      });
  }

  public getOrders() : Promise<Order[]> {
    return this.ahttp.get('http://localhost:8080/api/order').toPromise()
      .then(r => {
        console.log(r);
        console.log(r.json());
        return r.json();
      }).catch(r => {
        console.log("error: " + r);
      });
  }

  public getOrder(id: number): Promise<Order> {
    return this.ahttp.get('http://localhost:8080/api/order/' + id).toPromise().then(r => r.json()).catch(r => {
      console.log(r);
      return Promise.reject(r);
    });
  }
}
