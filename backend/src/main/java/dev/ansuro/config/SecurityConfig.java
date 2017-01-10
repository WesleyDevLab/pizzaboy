package dev.ansuro.config;

import dev.ansuro.security.FailureHandler;
import dev.ansuro.security.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;




/**
 *
 * @author Andy
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private SuccessHandler successHandler;
    
    @Autowired
    private FailureHandler failureHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin@pizzaboy.de").password("1234").roles("ADMIN");
    }

    
    
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO check again
        http.csrf().disable();
        http.cors()
        
        
            .and()
                .formLogin()
                .loginProcessingUrl("/api/authentication")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
            .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN");
    }
}
