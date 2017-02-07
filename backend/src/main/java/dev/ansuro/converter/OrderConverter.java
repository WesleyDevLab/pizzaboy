package dev.ansuro.converter;

import dev.ansuro.domain.Order;
import dev.ansuro.rest.dto.OrderDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andy
 */
@Component
public class OrderConverter implements Converter<Order, OrderDTO> {

    @Override
    public OrderDTO convert(final Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCreated(order.getCreated());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setNitems(order.getItems().stream().mapToInt(i -> i.getQuantity()).sum());
        
        return orderDTO;
    }
    
}
