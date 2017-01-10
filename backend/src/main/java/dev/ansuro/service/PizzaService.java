package dev.ansuro.service;

import dev.ansuro.domain.Pizza;
import dev.ansuro.repository.PizzaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andy
 */
@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;
    
    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }
    
}
