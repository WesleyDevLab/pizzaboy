import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import { Order } from './order.model';

@Injectable()
export class OrderService {


  constructor(private http: Http) { }

  public order(o: Order) {
    console.log(o);
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});

    this.http.post('http://localhost:8080/api/order', o, options).subscribe();
  }
}
