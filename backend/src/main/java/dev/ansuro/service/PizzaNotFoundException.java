package dev.ansuro.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Andy
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "pizza not found")
public class PizzaNotFoundException extends RuntimeException {

    public PizzaNotFoundException() {
    }
    
}
