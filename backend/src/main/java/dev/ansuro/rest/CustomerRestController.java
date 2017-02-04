package dev.ansuro.rest;

import dev.ansuro.domain.Customer;
import dev.ansuro.service.CustomerService;
import java.net.URI;
import java.security.Principal;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andy
 */
@RestController
@RequestMapping("/api")
public class CustomerRestController {
    @Autowired
    private Logger log;
    
    @Autowired
    private CustomerService customerService;
    
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(path = "/customer", method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        log.debug(customer.toString());
        
        Customer c = customerService.createCustomer(customer);
        
        return ResponseEntity.created(URI.create("/api/user/" + c.getId())).body(c);
    }
    
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(path = "/customer", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomer() {
        log.debug("TEST");
        Customer current = customerService.getCurrent();
        return ResponseEntity.ok(current);
    }
}
