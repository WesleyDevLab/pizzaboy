package dev.ansuro.rest;

import dev.ansuro.domain.Ingredient;
import dev.ansuro.domain.Pizza;
import dev.ansuro.service.PizzaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Pizza> add(Pizza pizza) {
        return null;
    }
}
