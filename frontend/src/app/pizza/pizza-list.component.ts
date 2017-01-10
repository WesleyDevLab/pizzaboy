import { Component, OnInit } from '@angular/core';
import { PizzaService } from './pizza.service';
import { ShoppingCartService } from '../shopping-cart/shopping-cart.service';

import { Pizza } from './pizza.model';

@Component({
  selector: 'pizza-list',
  templateUrl: './pizza-list.component.html',
  styleUrls: ['./pizza-list.component.css'],
  providers: []
})
export class PizzaListComponent implements OnInit {
  public pizzas: Pizza[];
  errorMessage: string;

  constructor(private pizzaService: PizzaService, private cartService: ShoppingCartService) { }

  ngOnInit() {
    this.getPizzas();
  }

  // TODO input validation
  addToCart(pizza: Pizza, quantity: string) {
    console.log(pizza);
    console.log(quantity);
    this.cartService.addToCart(pizza, parseInt(quantity));
  }

  getPizzas() {
    this.pizzaService.getPizzas().subscribe(pizzas => this.pizzas = pizzas, error => this.errorMessage = <any>error);
  }

}
