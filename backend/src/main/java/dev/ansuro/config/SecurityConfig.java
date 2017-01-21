package dev.ansuro.config;

import dev.ansuro.security.FailureHandler;
import dev.ansuro.security.RestAuthEntryPoint;
import dev.ansuro.security.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




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
    private UserDetailsService userDetailsService;
    
    @Autowired
    private RestAuthEntryPoint authEntryPoint;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("admin@pizzaboy.de").password("1234").roles("ADMIN");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO check again
        http.csrf().disable();
        http.cors()
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
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
