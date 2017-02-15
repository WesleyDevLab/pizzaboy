package dev.ansuro.service;

import dev.ansuro.domain.Ingredient;
import dev.ansuro.repository.IngredientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andy
 */
@Service
@Transactional(readOnly = true)
public class IngredientService {
    
    @Autowired
    private IngredientRepository ingredientRepository;
    
    @Transactional
    public Ingredient createOrUpdateIngredient(Ingredient ingredient) {
        return ingredientRepository.saveAndFlush(ingredient);
    }
    
    @Transactional
    public void updateIngredient() {
        
    }
    
    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }
    
    @Transactional
    public void deleteIngredient() {
        
    }
}
