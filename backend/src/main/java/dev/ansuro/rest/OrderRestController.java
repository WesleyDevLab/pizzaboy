package dev.ansuro.rest;

import dev.ansuro.domain.Order;
import dev.ansuro.rest.dto.OrderDTO;
import dev.ansuro.service.OrderService;
import java.net.URI;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
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
        
        return ResponseEntity.created(URI.create("/api/order/" + createdOrder.getId())).build();
    }
    
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(path = "/order", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDTO>> getOrders() {
        List<OrderDTO> l = orderService.getOrders();
        return ResponseEntity.ok(l);
    }
    
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(path = "/order/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderDTO> getOrderDetails(@PathVariable String id) {
        OrderDTO orderDTO = orderService.getOrderDetails(id);
        
        return ResponseEntity.ok(orderDTO);
    }
}
