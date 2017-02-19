import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

import { Pizza } from './pizza.model';

@Injectable()
export class PizzaService {

  constructor(private ahttp: AuthHttp) { }

  public getPizzas(): Observable<Pizza[]> {
    return this.ahttp.get('http://localhost:8080/api/pizzas')
      .map(this.getData)
      .catch(this.handleError);
  }

  private getData(res: Response) {
    let body = res.json();
    console.log(body);
    return body;
  }

  private handleError (error: Response | any) {
    // In a real world app, we might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    //console.error(errMsg);
    return Observable.throw(errMsg);
  }

  public getPizza(id: number): Promise<Pizza> {
    return this.ahttp.get('http://localhost:8080/api/admin/pizza/' + id).toPromise().then(r => r.json()).catch(e => {
      console.log("pizza not found. id: " + id);
      return Promise.reject(e);
    });
  }

  public save(p: Pizza): Promise<Response> {
    console.log("save " + p);
    return this.ahttp.post('http://localhost:8080/api/admin/pizza', JSON.stringify(p)).toPromise();
  }
}
