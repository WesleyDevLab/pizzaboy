package dev.ansuro.rest.dto;

/**
 *
 * @author Andy
 */
public class OrderItemDTO {
    private String ordernumber;
    private int quantity;
    private String name;
    private double price;

    public OrderItemDTO() {
    }

    public OrderItemDTO(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public OrderItemDTO(String ordernumber, int quantity) {
        this.ordernumber = ordernumber;
        this.quantity = quantity;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" + "ordernumber=" + ordernumber + ", quantity=" + quantity + '}';
    }
}
