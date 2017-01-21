package dev.ansuro.converter;

import dev.ansuro.domain.OrderItem;
import dev.ansuro.domain.Pizza;
import dev.ansuro.repository.PizzaRepository;
import dev.ansuro.rest.dto.OrderItemDTO;
import dev.ansuro.service.PizzaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andy
 */
@Component
public class OrderItemDTOConverter implements Converter<OrderItemDTO, OrderItem> {

    @Autowired
    private PizzaRepository pizzaRepository;
    
    @Override
    public OrderItem convert(OrderItemDTO orderitem) {
        OrderItem oi = new OrderItem();
        oi.setQuantity(orderitem.getQuantity());
        
        Pizza p = pizzaRepository.findOneByOrdernumber(orderitem.getOrdernumber()).orElseThrow(() -> new PizzaNotFoundException());
        oi.setPizza(p);
        return oi;
    }
    
}
