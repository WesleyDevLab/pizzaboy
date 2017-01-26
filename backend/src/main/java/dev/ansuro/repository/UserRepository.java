package dev.ansuro.repository;

import dev.ansuro.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Andy
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    //@Query("select u from User u where u.customer.mail = ?1")
    Optional<User> findOneByMail(String email);
    
    Optional<User> findOneById(Long id);
    
    @Query("select u from _User u where u.mail = ?#{principal.username}")
    Optional<User> findCurrentUser();
}
