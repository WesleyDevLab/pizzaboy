package dev.ansuro.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.ansuro.domain.Customer;
import java.time.ZonedDateTime;
import java.util.List;

/**
 *
 * @author Andy
 */
//@JsonIgnoreProperties
public class OrderDTO {
    private Long id;
    private Customer customer;
    private List<OrderItemDTO> items;
    private double totalPrice;
    private int nitems;
    
    @JsonFormat(pattern = "dd.MM.yyyy - HH:mm")
    private ZonedDateTime created;

    public OrderDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public int getNitems() {
        return nitems;
    }

    public void setNitems(int nitems) {
        this.nitems = nitems;
    }
    
    @Override
    public String toString() {
        return "OrderDTO{" + "customer=" + customer + ", items=" + items + '}';
    }
}
