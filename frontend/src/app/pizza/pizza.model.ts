import { Ingredient } from '../admin/ingredient-edit/ingredient.model';

export class Pizza {
  public id: number;
  public name: String;
  public price: number;
  public ordernumber: number;
  public ingredients: Ingredient[];

  constructor(id: number, ordernumber: number, name: String, price: number, ingredients: Ingredient[]) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.ordernumber = ordernumber;
    this.ingredients = ingredients;
   }

}
