package dev.ansuro.converter;

import dev.ansuro.domain.Order;
import dev.ansuro.rest.dto.OrderDTO;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andy
 */
@Component
public class OrderDetailsDTOConverter implements Converter<Order, OrderDTO>{

    @Autowired
    private OrderItemConverter orderItemConverter;
    
    @Override
    public OrderDTO convert(final Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCreated(order.getCreated());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setNitems(order.getItems().stream().mapToInt(i -> i.getQuantity()).sum());
        orderDTO.setItems(order.getItems().stream().map(x -> orderItemConverter.convert(x)).collect(Collectors.toList()));
        
        orderDTO.setCustomer(order.getCustomer());
        
        return orderDTO;
    }
    
}
