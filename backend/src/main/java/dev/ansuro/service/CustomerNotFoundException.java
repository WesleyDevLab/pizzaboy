package dev.ansuro.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Andy
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "customer not found")
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
    }
    
}
