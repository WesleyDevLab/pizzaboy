package dev.ansuro.repository;

import dev.ansuro.domain.Pizza;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andy
 */
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    
    Optional<Pizza> findOneByOrdernumber(String on);
    Optional<Pizza> findOneById(Long id);
}
