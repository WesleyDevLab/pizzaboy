package dev.ansuro.rest;

import dev.ansuro.domain.User;
import dev.ansuro.service.UserService;
import java.net.URI;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class UserRestController {
    @Autowired
    private Logger log;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.debug(user.toString());
        User createdUser = userService.registerUser(user);
        
        return ResponseEntity.created(URI.create("/api/user/" + createdUser.getId())).body(createdUser);
    }
    
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public ResponseEntity<User> getUser() {
        User u = userService.findCurrentUser();
        return ResponseEntity.ok(u);
    }
}
