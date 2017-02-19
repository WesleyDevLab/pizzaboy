import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PizzaService } from './pizza.service';
import { ShoppingCartService } from '../shopping-cart/shopping-cart.service';
import { AuthenticationService } from '../authentication/authentication.service';

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
  // just to init the spinner
  initvalue: number = 1;

  constructor(private pizzaService: PizzaService, private cartService: ShoppingCartService, private authService: AuthenticationService, private router: Router) { }

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

  public isAdmin(): boolean {
    return this.authService.isAdmin();
  }

  public edit(p: Pizza) {
    this.router.navigate(['/admin/pizza', p.id]);
  }
}
