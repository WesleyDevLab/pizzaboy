package dev.ansuro.service;

import dev.ansuro.domain.Customer;
import dev.ansuro.domain.User;
import dev.ansuro.repository.AuthorityRepository;
import dev.ansuro.repository.CustomerRepository;
import dev.ansuro.repository.UserRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andy
 */
@Service
@Transactional(readOnly = true)
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Transactional
    public User registerUser(User user) {
        userRepository.findOneByMail(user.getMail()).ifPresent(x -> {
            throw new EmailAlreadyRegisterdException();
        });

//        if(user.getCustomer() != null) {
//            Customer c = customerRepository.saveAndFlush(user.getCustomer());
//            user.addCustomer(c);
//        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(Arrays.asList(authorityRepository.findByName("USER")));
        
        return userRepository.saveAndFlush(user);
    }
}
