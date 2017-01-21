package dev.ansuro.repository;

import dev.ansuro.domain.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andy
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    Optional<Customer> findOneByUserMail(String mail);
    
}
