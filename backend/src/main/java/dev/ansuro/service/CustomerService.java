package dev.ansuro.service;

import dev.ansuro.converter.CustomerConverter;
import dev.ansuro.domain.Customer;
import dev.ansuro.domain.User;
import dev.ansuro.repository.CustomerRepository;
import dev.ansuro.repository.UserRepository;
import dev.ansuro.rest.dto.CustomerDTO;
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
    
    @Autowired
    private CustomerConverter customerConverter;

    @Transactional
    public Customer createCustomer(Customer customer) {
        log.debug(customer.toString());
        User u = userRepository.findOneByMail(SecurityUtil.getCurrentUsername())
                .orElseThrow(() -> new UserNotFoundException());
        Customer c = customerRepository.saveAndFlush(customer);
        u.setCustomer(c);
        userRepository.saveAndFlush(u);
        
        return c;
    }

    public CustomerDTO getCurrent() {
        User u = userRepository.findOneByMail(SecurityUtil.getCurrentUsername())
                .orElseThrow(() -> new UserNotFoundException());
        
        Customer customer = u.getCustomer();
        if(customer == null)
            throw new CustomerNotFoundException();

        log.info(customer.toString());
        return customerConverter.convert(customer);
    }
}
