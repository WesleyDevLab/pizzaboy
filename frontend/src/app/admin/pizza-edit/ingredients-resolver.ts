import { Injectable } from '@angular/core';
import { Router, Resolve, ActivatedRouteSnapshot } from '@angular/router';

import { Ingredient } from '../ingredient-edit/ingredient.model';
import { IngredientService } from '../ingredient-edit/ingredient.service';

@Injectable()
export class IngredientsResolver implements Resolve<Ingredient[]> {
    
    constructor(private ingService: IngredientService, private router: Router) {}

    resolve(route: ActivatedRouteSnapshot): Promise<Ingredient[]> {
        
        return this.ingService.getIngredients();
    }
}