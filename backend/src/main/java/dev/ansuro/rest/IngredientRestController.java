package dev.ansuro.rest;

import dev.ansuro.domain.Ingredient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andy
 */
@RestController
@RequestMapping(path = "/api")
public class IngredientRestController {
    
    @RequestMapping(path = "/ingredient/{name}", method = RequestMethod.GET)
    public Ingredient getIngredient(@PathVariable String name) {
        return null;
    }
    
    @RequestMapping(path = "/admin/ingredient", method = RequestMethod.POST)
    public ResponseEntity<Ingredient> add(Ingredient ingredient) {
        return null;
    }
    
}
