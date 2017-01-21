package dev.ansuro.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Andy
 */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "already registerd")
public class EmailAlreadyRegisterdException extends RuntimeException {

    public EmailAlreadyRegisterdException() {
    }
    
}
