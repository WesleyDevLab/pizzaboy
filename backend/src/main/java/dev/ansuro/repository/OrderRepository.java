package dev.ansuro.repository;

import dev.ansuro.domain.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Andy
 */

public interface OrderRepository extends JpaRepository<Order, Long> {
    
    @Query("select o from _Order o where o.user.mail = ?#{authentication.name}")
    List<Order> getOrdersFromCurrentUser();
}
