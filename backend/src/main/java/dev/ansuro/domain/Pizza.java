package dev.ansuro.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Andy
 */
@Entity
public class Pizza implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String ordernumber;
    
    @NotNull
    private String name;
    
    @Digits(integer = 4, fraction = 2)
    private double price;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;

    public Pizza() {
    }

    public Pizza(String ordernumber, String name, double price) {
        this.ordernumber = ordernumber;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
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
    
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    
}
