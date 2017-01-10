package dev.ansuro.rest.dto;

import dev.ansuro.domain.Order;

/**
 *
 * @author Andy
 */
public class OrderDTO extends Order {
    private boolean register;
    private String password;

    public OrderDTO() {
    }

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "register=" + register + ", password=" + password + " " + super.toString() + '}';
    }

}
