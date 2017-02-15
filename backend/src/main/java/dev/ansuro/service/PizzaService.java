package dev.ansuro.service;

import dev.ansuro.domain.Pizza;
import dev.ansuro.repository.PizzaRepository;
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
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;
    
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    @Transactional
    public Pizza createPizza(Pizza pizza) {
        return pizzaRepository.saveAndFlush(pizza);
    }
    
    
}
