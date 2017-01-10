package dev.ansuro.service;

import dev.ansuro.repository.CustomerRepository;
import dev.ansuro.repository.UserRepository;
import dev.ansuro.rest.dto.OrderDTO;
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
    private CustomerRepository customerRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public void saveOrder(OrderDTO orderDTO) {
        
    }
}
