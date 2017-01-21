package dev.ansuro.rest;

import dev.ansuro.domain.Order;
import dev.ansuro.rest.dto.OrderDTO;
import dev.ansuro.service.OrderService;
import java.net.URI;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    
    @RequestMapping(path = "/order", method = RequestMethod.POST)
    public ResponseEntity<Order> saveOrder(@RequestBody OrderDTO order) {
        log.debug("TEST: {}", order);
        
        Order createdOrder = orderService.createOrder(order);
        
        return ResponseEntity.created(URI.create("/api/order/" + createdOrder.getId())).body(createdOrder);
    }
}
