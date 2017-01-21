package dev.ansuro.repository;

import dev.ansuro.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Andy
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
    
    Authority findByName(String name);
    
}
