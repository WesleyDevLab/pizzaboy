import { Injectable } from '@angular/core';
import { AuthHttp } from 'angular2-jwt';
import { Ingredient } from './ingredient.model';

@Injectable()
export class IngredientService {

  constructor(private ahttp: AuthHttp) { }

  public getIngredients(): Promise<Ingredient[]> {
    console.log("getIngredients");
    return this.ahttp.get('http://localhost:8080/api/admin/ingredient').toPromise().then(r => r.json()).catch(r => Promise.reject(r));
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
