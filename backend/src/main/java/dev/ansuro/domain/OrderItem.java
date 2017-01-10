package dev.ansuro.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Andy
 */
@Entity
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne(targetEntity = Order.class)
    private Long pizzaId;
    
    private int quantity;

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(Long pizzaId) {
        this.pizzaId = pizzaId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "id=" + id + ", pizzaId=" + pizzaId + ", quantity=" + quantity + '}';
    }
}
