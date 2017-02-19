package dev.ansuro.rest;

import dev.ansuro.domain.Pizza;
import dev.ansuro.service.PizzaService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "/api")
public class PizzaRestController {
    
    @Autowired
    private PizzaService pizzaService;
    
    @RequestMapping(path = "/pizzas", method = RequestMethod.GET)
    public List<Pizza> getAll() {
        
        return pizzaService.findAll();    
    }
    
    @RequestMapping(path = "/admin/pizza", method = RequestMethod.POST)
    public ResponseEntity<Pizza> add(@RequestBody Pizza pizza) {
        Pizza p = pizzaService.createPizza(pizza);
        return ResponseEntity.created(URI.create("/api/admin/pizza" + p.getId())).build();
    }
    
    @RequestMapping(path = "/admin/pizza/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pizza> getPizza(@PathVariable String id) {
        Pizza p = pizzaService.getPizza(id);
        return ResponseEntity.ok(p);
    }
}
