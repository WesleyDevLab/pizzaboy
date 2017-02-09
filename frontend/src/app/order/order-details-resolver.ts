import { Injectable } from '@angular/core';
import { Router, Resolve, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';

import { Order } from './order.model';
import { OrderService } from './order.service';

@Injectable()
export class OrderDetailsResolver implements Resolve<Order> {
    
    constructor(private orderService: OrderService, private router: Router) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<Order> {
        let id = route.params['id'];

        return this.orderService.getOrder(id).then(order => order).catch(() => {
            this.router.navigate(['/user/orders']);
            return null;
        });
    }
}
