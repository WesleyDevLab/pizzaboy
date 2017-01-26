package dev.ansuro.service;

import dev.ansuro.domain.Customer;
import dev.ansuro.domain.Order;
import dev.ansuro.domain.User;
import dev.ansuro.repository.CustomerRepository;
import dev.ansuro.repository.OrderRepository;
import dev.ansuro.repository.UserRepository;
import dev.ansuro.rest.dto.OrderDTO;
import dev.ansuro.security.SecurityUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andy
 */
@Service
@Transactional(readOnly = true)
public class OrderService {

    @Autowired
    private Logger log;

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public Order createOrder(Order order) {
        
        log.debug("order: " + order);
        if(order.getCustomer() == null) {
            throw new CustomerNotFoundException();
        }

        if(SecurityUtil.isAuthenticated()) {
            // check if saved customer equals sent data and find it in database
            User currentUser = userRepository.findCurrentUser().orElseThrow(() -> new UserNotFoundException());
            
            Customer customer = currentUser.getCustomer();
            
            if(customer != null && !customer.equals(order.getCustomer())) {
                log.debug("!equals");
                log.debug(customer.toString());
                currentUser.setCustomer(order.getCustomer());
                userRepository.saveAndFlush(currentUser);
            }
        }
        return orderRepository.saveAndFlush(order);
    }
}
