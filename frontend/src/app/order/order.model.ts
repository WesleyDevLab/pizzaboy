import { Customer } from '../customer/customer.model';
import { OrderItem } from './order-item.model';

export class Order {
    customer: Customer;
    items: OrderItem[] = [];
    register: boolean;
    password?: string;

    constructor() {
        
    }
}