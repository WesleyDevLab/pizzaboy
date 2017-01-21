import { User } from '../authentication/user.model';

export class Customer {
    public id: number;
    public firstname: string;
    public lastname: string;
    public street: string;
    public housenumber: string;
    public zip: number;
    public city: string;
    public phone: string;
    public user?: User;
}