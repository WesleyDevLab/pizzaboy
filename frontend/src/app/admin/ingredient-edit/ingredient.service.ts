import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
import { Observable } from 'rxjs/Observable'
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { Ingredient } from './ingredient.model';

@Injectable()
export class IngredientService {

  constructor(private ahttp: AuthHttp) { }

  public getIngredients() {
    console.log("getIngredients");
    return this.ahttp.get('http://localhost:8080/api/admin/ingredient').map(r => r.json()).catch(e => {
      console.info(e);
      return Observable.throw(e);
    });
  }

  public save(ing: Ingredient) {
    return this.ahttp.post('http://localhost:8080/api/admin/ingredient', JSON.stringify(ing)).toPromise()
      .then(r => console.log(r))
      .catch(r => {
        console.log("error: " + r);
        return Promise.reject(r);
      });
  }
}
