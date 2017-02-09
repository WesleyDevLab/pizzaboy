package dev.ansuro.converter;

import dev.ansuro.domain.OrderItem;
import dev.ansuro.rest.dto.OrderItemDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andy
 */
@Component
public class OrderItemConverter implements Converter<OrderItem, OrderItemDTO> {

    @Override
    public OrderItemDTO convert(OrderItem oi) {
        OrderItemDTO oidto = new OrderItemDTO();
        oidto.setQuantity(oi.getQuantity());
        oidto.setName(oi.getPizza().getName());
        oidto.setPrice(oi.getPizza().getPrice());
        oidto.setOrdernumber(oi.getPizza().getOrdernumber());
        
        return oidto;
    }
    
}
