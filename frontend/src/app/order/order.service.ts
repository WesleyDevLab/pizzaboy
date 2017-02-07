import { Injectable } from '@angular/core';
import { Response, Headers, RequestOptions } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';

import { Order } from './order.model';

@Injectable()
export class OrderService {


  constructor(private ahttp: AuthHttp) { }

  public order(o: Order): Promise<void> {
    console.log(o);
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});

    return this.ahttp.post('http://localhost:8080/api/order', o, options).toPromise()
      .then(r => {
        console.log(r);
      }).catch(r => {
        console.log(r);
        Promise.reject(r);
      });
  }
}
