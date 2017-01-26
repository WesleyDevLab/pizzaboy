package dev.ansuro.rest.dto;

import dev.ansuro.domain.Customer;
import java.util.List;

/**
 *
 * @author Andy
 */
public class OrderDTO {
    private Customer customer;
    private List<OrderItemDTO> items;

    public OrderDTO() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "customer=" + customer + ", items=" + items + '}';
    }
}
