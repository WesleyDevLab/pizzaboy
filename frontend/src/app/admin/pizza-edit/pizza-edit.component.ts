import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Message } from 'primeng/primeng';

import { PizzaService } from '../../pizza/pizza.service';
import { IngredientService } from '../../admin/ingredient-edit/ingredient.service';

import { Pizza } from '../../pizza/pizza.model';
import { Ingredient } from '../ingredient-edit/ingredient.model';

@Component({
  selector: 'app-pizza-edit',
  templateUrl: './pizza-edit.component.html',
  styleUrls: ['./pizza-edit.component.css']
})
export class PizzaEditComponent implements OnInit {
  p: Pizza;
  msgs: Message[] = [];
  ingredients: Ingredient[];

  constructor(private route: ActivatedRoute, private router: Router, private pizzaService: PizzaService, private ingService: IngredientService) { }

  ngOnInit() {
    this.route.data.subscribe((data: {pizza: Pizza, ingredients: Ingredient[]}) => {
      this.p = data.pizza;
      this.p.ingredients.forEach(i1 => {
        data.ingredients.forEach((i2, index) => {
          if(i1.id == i2.id) {
            data.ingredients.splice(index, 1);
            //console.log("removed:" + index);
          }
        });
      });
      this.ingredients = data.ingredients;
      /*data.ingredients.forEach(x => {
        this.ingredients.push({label: x.name, value: x.id});
      });*/
/*      this.p.ingredients.forEach(x => {
        this.selectedIngredients.push(x.id);
      });*/
    });
  }

  public save() {
    console.log("save");
    this.pizzaService.save(this.p).then(r => {
      //r. check if created or updated in response
      this.msgs.push({severity: 'success', summary: 'Pizza saved'});
    }).catch(r => {
      this.msgs.push({severity: 'error', summary: 'save failed'});
    });
  }
}
