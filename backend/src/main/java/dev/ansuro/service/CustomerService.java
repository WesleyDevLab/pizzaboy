package dev.ansuro.service;

import dev.ansuro.domain.Customer;
import dev.ansuro.domain.User;
import dev.ansuro.repository.CustomerRepository;
import dev.ansuro.repository.UserRepository;
import dev.ansuro.security.SecurityUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andy
 */
@Service
@Transactional(readOnly = true)
public class CustomerService {
    @Autowired
    private Logger log;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Customer createCustomer(Customer customer) {
        log.debug(customer.toString());
        User u = userRepository.findOneByMail(SecurityUtil.getCurrentUsername())
                .orElseThrow(() -> new UserNotFoundException());
        //customer.setUser(u);
        Customer c = customerRepository.saveAndFlush(customer);
        
        return c;
    }
}
