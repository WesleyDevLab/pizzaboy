package dev.ansuro.service;

import dev.ansuro.domain.User;
import dev.ansuro.repository.AuthorityRepository;
import dev.ansuro.repository.CustomerRepository;
import dev.ansuro.repository.UserRepository;
import dev.ansuro.security.SecurityUtil;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andy
 */
@Service
@Transactional(readOnly = true)
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Transactional
    public User registerUser(User user) {
        userRepository.findOneByMail(user.getMail()).ifPresent(x -> {
            throw new EmailAlreadyRegisterdException();
        });
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(Arrays.asList(authorityRepository.findByName("USER")));
        User u = userRepository.saveAndFlush(user);
        
//        // authenticate user
//        UserDetails ud = userDetailsService.loadUserByUsername(u.getMail());
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(ud, ud.getPassword(), ud.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(token);
        
        return u;
    }
    
    public User findCurrentUser() {
        return userRepository.findOneByMail(SecurityUtil.getCurrentUsername()).get();
    }
}
