
export class Pizza {
  public id: number;
  public name: String;
  public price: number;
  public ordernumber: number;

  constructor(id: number, ordernumber: number, name: String, price: number) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.ordernumber = ordernumber;
   }

}
