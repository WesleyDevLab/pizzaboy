package dev.ansuro.service;

import dev.ansuro.domain.Customer;
import dev.ansuro.domain.Order;
import dev.ansuro.repository.CustomerRepository;
import dev.ansuro.repository.OrderRepository;
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
    private CustomerRepository customerRepository;
    
    @Autowired
    private Converter<OrderDTO, Order> orderDTOConverter;

    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        Order order = orderDTOConverter.convert(orderDTO);
        
        log.debug("order: " + order);
        // When the user is logged in and no different customer data where send,
        // use the saved, if available, else throw exception
        if (order.getCustomer() != null) {
            log.debug("customer data is set " + order.getCustomer().toString());
            Customer customer = order.getCustomer();
            Customer c = customerRepository.saveAndFlush(customer);
            order.setCustomer(c);
        } else if (SecurityUtil.isAuthenticated()) {
            log.debug("no customer data set, using saved customer");
            // user is logged in and the saved delivery address should be used
            Customer c = customerRepository
                    .findOneByUserMail(SecurityUtil.getCurrentUser().getUsername())
                    .orElseThrow(() -> new CustomerNotFoundException());

            order.setCustomer(c);
        } else {
            log.debug("no customer data found!");
            throw new CustomerNotFoundException();
        }

        return orderRepository.saveAndFlush(order);
    }
}
