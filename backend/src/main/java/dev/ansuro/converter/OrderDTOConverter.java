package dev.ansuro.converter;

import dev.ansuro.domain.Order;
import dev.ansuro.rest.dto.OrderDTO;
import dev.ansuro.rest.dto.OrderItemDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andy
 */
@Component
public class OrderDTOConverter implements Converter<OrderDTO, Order> {

    @Autowired
    private OrderItemDTOConverter orderItemDTOConverter;
    
    @Transactional(readOnly = true)
    @Override
    public Order convert(OrderDTO orderDTO) {
        Order o = new Order();
        o.setCustomer(orderDTO.getCustomer());
        
        List<OrderItemDTO> items = orderDTO.getItems();
        
        o.setItems(items.stream()
                    .map(i -> orderItemDTOConverter.convert(i))
                    .collect(Collectors.toList())
        );
        
        return o;
    }
    
}
