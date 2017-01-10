package dev.ansuro.rest;

import dev.ansuro.domain.Order;
import dev.ansuro.repository.OrderRepository;
import dev.ansuro.rest.dto.OrderDTO;
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
    private OrderRepository orderRepository;
    
    @RequestMapping(path = "/order", method = RequestMethod.POST)
    public ResponseEntity<Order> saveOrder(@RequestBody OrderDTO orderDTO) {
        log.debug("TEST: {}", orderDTO);
        
        // check if mail already registerd else register
        
        // check order items
        
        // save order
        
        // TODO tbd later: notify deliveryguy view (SSE)
        
        
        
        //orderRepository.saveAndFlush(order);
        
        return ResponseEntity.ok().build();
    }
}
