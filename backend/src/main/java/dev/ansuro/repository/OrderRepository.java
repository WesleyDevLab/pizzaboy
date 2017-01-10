package dev.ansuro.repository;

import dev.ansuro.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andy
 */

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
