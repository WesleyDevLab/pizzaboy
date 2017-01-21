package dev.ansuro.rest.dto;

/**
 *
 * @author Andy
 */
public class OrderItemDTO {
    private String ordernumber;
    private int quantity;

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
}
