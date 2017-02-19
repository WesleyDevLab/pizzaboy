import { Component, OnInit } from '@angular/core';

import { Message } from 'primeng/primeng';

import { Ingredient } from './ingredient.model';
import { IngredientService } from './ingredient.service'

@Component({
  selector: 'app-ingredient-edit',
  templateUrl: './ingredient-edit.component.html',
  styleUrls: ['./ingredient-edit.component.css']
})
export class IngredientEditComponent implements OnInit {
  displayForm: boolean = false;
  selectedIng: Ingredient;
  ingredients: Ingredient[];
  msgs: Message[] = [];

  constructor(private ingService: IngredientService) {}

  ngOnInit() {
    this.selectedIng = new Ingredient();
    this.load();
  }

  private load() {
    console.log("load");
    this.ingService.getIngredients().then(i => this.ingredients = i).catch(err => console.log(err));
  }

  public save() {
    console.log(this.selectedIng);
    this.ingService.save(this.selectedIng).then(() => {
      this.load();
      this.displayForm = false;
    }).catch(r => {
      this.msgs.push({severity: 'error', summary: 'save failed', detail: 'save failed'});
    });
    
  }

  public onRowSelect(evt) {
    console.log(evt);
    this.selectedIng.id = evt.data.id;
    this.selectedIng.name = evt.data.name;
    this.selectedIng.description = evt.data.description;
    this.displayForm = true;
  }

  public showAddDialog() {
    this.selectedIng = new Ingredient();

    this.displayForm = true;
  }

  public close() {
    this.displayForm = false;
  }

}
