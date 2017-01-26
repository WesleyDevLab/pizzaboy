package dev.ansuro.config;

import dev.ansuro.domain.Authority;
import dev.ansuro.domain.Ingredient;
import dev.ansuro.domain.Pizza;
import dev.ansuro.domain.User;
import dev.ansuro.repository.AuthorityRepository;
import dev.ansuro.repository.IngredientRepository;
import dev.ansuro.repository.PizzaRepository;
import dev.ansuro.repository.UserRepository;
import java.util.Arrays;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andy
 */
@Component
public class DevDBInit {
    @Autowired
    private Logger log;
    
    @Autowired
    private IngredientRepository ingredientRepository;
    
    @Autowired
    private PizzaRepository pizzaRepository;
    
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("Dev DB init");
        
        if(pizzaRepository.count() == 0) {
            Ingredient tomatoSauce = new Ingredient("Tomato Sauce", "Tomato Sauce");
            /*tomatoSauce = */ingredientRepository.save(tomatoSauce);
            Ingredient blackOlives = new Ingredient("Black Olives", "Black Olives");
            ingredientRepository.save(blackOlives);
            Ingredient whiteOnions = new Ingredient("White Onions", "White Onions");
            ingredientRepository.save(whiteOnions);
            Ingredient redOnions = new Ingredient("Red Onions", "Red Onions");
            ingredientRepository.save(redOnions);
            Ingredient garlic = new Ingredient("Garlic", "Fresh Garlic");
            ingredientRepository.save(garlic);
            Ingredient bacon = new Ingredient("Bacon", "Crispy Pork Bacon");
            ingredientRepository.save(bacon);
            Ingredient paprika = new Ingredient("Paprika", "Red Paprika");
            ingredientRepository.save(paprika);
            Ingredient cheese = new Ingredient("Cheese", "Emmentaler and Gouda");
            ingredientRepository.save(cheese);
            Ingredient mozzarella = new Ingredient("Mozzarella", "Italian Mozzarella (buffalo milk)");
            ingredientRepository.save(mozzarella);
            Ingredient basil = new Ingredient("Basil", "Fresh Basil");
            ingredientRepository.save(basil);
            
            Pizza pizza = new Pizza("001", "Margherita", 13.00);
            pizza.setIngredients(Arrays.asList(tomatoSauce, cheese, garlic, basil));
            pizzaRepository.save(pizza);
            
            pizza = new Pizza("002", "Cheese", 10.00);
            pizza.setIngredients(Arrays.asList(tomatoSauce, cheese));
            pizzaRepository.save(pizza);
            
            pizza = new Pizza("003", "Vegetarian", 10.00);
            pizza.setIngredients(Arrays.asList(tomatoSauce, mozzarella, garlic, blackOlives, paprika, redOnions));
            pizzaRepository.save(pizza);
        }
        
        if(authorityRepository.count() == 0) {
            Authority authority = new Authority("USER");
            authorityRepository.save(authority);
            
            authority = new Authority("ADMIN");
            authorityRepository.save(authority);
        }
        
        if(userRepository.count() == 0) {
            User user = new User();
            user.setMail("admin@pizzaboy.de");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setAuthorities(Arrays.asList(authorityRepository.findByName("USER"), authorityRepository.findByName("ADMIN")));
            userRepository.save(user);
        }
    }
}
