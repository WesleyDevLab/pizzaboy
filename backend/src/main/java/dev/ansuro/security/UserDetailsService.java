package dev.ansuro.security;

import dev.ansuro.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andy
 */
@Component
@Transactional(readOnly = true)
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByMail(username).map(u -> {
            List<GrantedAuthority> l = u.getAuthorities().stream()
                    .map(a -> new SimpleGrantedAuthority(a.getName()))
                    .collect(Collectors.toList());
            return new User(username, u.getPassword(), l);
        }).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        
    }
    
}
