package dev.ansuro.service;

import dev.ansuro.converter.OrderConverter;
import dev.ansuro.converter.OrderDTOConverter;
import dev.ansuro.converter.OrderDetailsDTOConverter;
import dev.ansuro.domain.Customer;
import dev.ansuro.domain.Order;
import dev.ansuro.domain.User;
import dev.ansuro.repository.CustomerRepository;
import dev.ansuro.repository.OrderRepository;
import dev.ansuro.repository.UserRepository;
import dev.ansuro.rest.dto.OrderDTO;
import dev.ansuro.security.SecurityUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.access.AccessDeniedException;
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
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private OrderConverter orderConverter;
    
    @Autowired
    private Converter<OrderDTO, Order> orderDTOConverter;
    
    @Autowired
    private OrderDetailsDTOConverter orderDetailsDTOConverter;
    
    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        Order order = orderDTOConverter.convert(orderDTO);
        
        log.debug("order: " + order);
        if(order.getCustomer() == null) {
            throw new CustomerNotFoundException();
        }

        Customer c;
        if(SecurityUtil.isAuthenticated()) {
            log.debug("authenticated");
            // check if saved customer equals sent data and find it in database
            User currentUser = userRepository.findOneByMail(SecurityUtil.getCurrentUsername())
                .orElseThrow(() -> new UserNotFoundException());
            order.setUser(currentUser);
            
            c = currentUser.getCustomer();
            
            if(c == null || !c.equals(order.getCustomer())) {
                log.debug("!equals");
                c = customerRepository.saveAndFlush(order.getCustomer());
                currentUser.setCustomer(c);
                userRepository.saveAndFlush(currentUser);
            }
        } else {
            c = customerRepository.saveAndFlush(order.getCustomer());
        }
        order.setCustomer(c);
        return orderRepository.saveAndFlush(order);
    }

    public List<OrderDTO> getOrders() {
        List<Order> ordersFromCurrentUser = orderRepository.getOrdersFromCurrentUser();
        return ordersFromCurrentUser.stream().map(o -> orderConverter.convert(o)).collect(Collectors.toList());
    }

    public OrderDTO getOrderDetails(String id) {
        Order o = orderRepository.findOneById(Long.valueOf(id)).orElseThrow(() -> new OrderNotFoundException());
        
        if(!o.getUser().getMail().equals(SecurityUtil.getCurrentUsername())) {
            throw new AccessDeniedException("");
        }
        OrderDTO odto = orderDetailsDTOConverter.convert(o);
        return odto;
    }
}
