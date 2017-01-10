import { Pizza } from '../pizza/pizza.model';

export class CartItem {
    constructor(public pizza: Pizza, public quantity: number) {
    }
}