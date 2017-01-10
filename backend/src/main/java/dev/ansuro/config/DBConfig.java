package dev.ansuro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Andy
 */
@Configuration
@EnableJpaRepositories("dev.ansuro.repository")
@EnableTransactionManagement
public class DBConfig {
    
}
