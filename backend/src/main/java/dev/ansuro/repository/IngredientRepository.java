package dev.ansuro.repository;

import dev.ansuro.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andy
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
    
}
