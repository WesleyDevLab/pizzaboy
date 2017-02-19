import { Injectable } from '@angular/core';
import { Router, Resolve, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';

import { Pizza } from '../../pizza/pizza.model';
import { PizzaService } from '../../pizza/pizza.service';

@Injectable()
export class PizzaEditResolver implements Resolve<Pizza> {
    
    constructor(private pizzaService: PizzaService, private router: Router) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<Pizza> {
        let id = route.params['id'];
        console.log("route id: " + id);
        if(id == 'new') {
            let p = new Pizza(0, 0, '', 0, []);
            return Promise.resolve(p);
        }

        return this.pizzaService.getPizza(id).then(pizza => pizza).catch(() => {
            console.log("pizza not found => catch");
            this.router.navigate(['/pizza']);
        });
    }
}