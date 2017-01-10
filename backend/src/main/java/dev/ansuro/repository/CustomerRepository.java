package dev.ansuro.repository;

import dev.ansuro.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andy
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
