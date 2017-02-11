import { Component, OnInit } from '@angular/core';

import { Ingredient } from './ingredient.model';
import { IngredientService } from './ingredient.service'

@Component({
  selector: 'app-ingredient-edit',
  templateUrl: './ingredient-edit.component.html',
  styleUrls: ['./ingredient-edit.component.css'],
  providers: [IngredientService]
})
export class IngredientEditComponent implements OnInit {
  isCollapsed: boolean = true;
  selectedIng: Ingredient;
  ingredients: Ingredient[];

  constructor(private ingService: IngredientService) {}

  ngOnInit() {
    this.selectedIng = new Ingredient();
    this.load();
  }

  private load() {
    console.log("load");
    this.ingService.getIngredients().subscribe(i => this.ingredients = i, err => console.log(err));
  }

  public save() {
    console.log("add");
    this.ingService.save(this.selectedIng);

    this.selectedIng = null;
  }

  public selectIng(ing: Ingredient) {
    this.selectedIng = ing;
    this.isCollapsed = false;
  }
}
