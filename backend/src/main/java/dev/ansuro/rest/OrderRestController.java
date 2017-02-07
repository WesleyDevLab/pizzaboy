package dev.ansuro.rest;

import dev.ansuro.domain.Order;
import dev.ansuro.rest.dto.OrderDTO;
import dev.ansuro.service.OrderService;
import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andy
 */
@RestController
@RequestMapping("/api")
public class OrderRestController {
    
    @Autowired
    private Logger log;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private Converter<OrderDTO, Order> orderDTOConverter;
    
    @RequestMapping(path = "/order", method = RequestMethod.POST)
    public ResponseEntity<Order> saveOrder(@RequestBody OrderDTO order) {
        log.debug("TEST: {}", order);
        
        Order createdOrder = orderService.createOrder(orderDTOConverter.convert(order));
        
        return ResponseEntity.created(URI.create("/api/order/" + createdOrder.getId())).build();
    }
    
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(path = "/order", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDTO>> getOrders() {
        List<OrderDTO> l = orderService.getOrders();
        return ResponseEntity.ok(l);
    }
}
