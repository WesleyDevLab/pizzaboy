package dev.ansuro.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Andy
 */
@Entity
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, updatable = false, name = "pizza_id")
    private Pizza pizza;
    
    @Min(1)
    @NotNull
    private int quantity;

    public OrderItem() {
    }

    public OrderItem(Pizza pizza, int quantity) {
        this.pizza = pizza;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "id=" + id + ", pizza=" + pizza + ", quantity=" + quantity + '}';
    }
}
