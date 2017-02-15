package dev.ansuro.rest;

import dev.ansuro.domain.Ingredient;
import dev.ansuro.service.IngredientService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
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
public class IngredientRestController {
    
    @Autowired
    private IngredientService ingredientService;
    
    @RequestMapping(path = "/ingredient/{name}", method = RequestMethod.GET)
    public Ingredient getIngredient(@PathVariable String name) {
        return null;
    }
    
    @RequestMapping(path = "/admin/ingredient", method = RequestMethod.POST)
    public ResponseEntity<Ingredient> createOrUpdate(@RequestBody @Valid Ingredient ingredient) {
        Ingredient i = ingredientService.createOrUpdateIngredient(ingredient);
        return ResponseEntity.created(URI.create("/api/ingredient/" + i.getId())).build();
    }
    
    @RequestMapping(path = "/admin/ingredient", method = RequestMethod.GET)
    public ResponseEntity<List<Ingredient>> getIngredients() {
        List<Ingredient> ingredients = ingredientService.getIngredients();
        return ResponseEntity.ok(ingredients);
    }
}
