import { Pizza } from "../pizza/pizza.model";

export class OrderItem {
    constructor(public ordernumber: number, public quantity: number) {}
}