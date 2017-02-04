package dev.ansuro.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author Andy
 */
public class AuthenticatedUser implements Authentication {
    private final String name;
    private final List<SimpleGrantedAuthority> authorities;
    private boolean authenticated;
    
    public AuthenticatedUser(String name, boolean admin) {
        this.name = name;
        this.authenticated = true;
        this.authorities = new ArrayList<>();
        this.authorities.add(new SimpleGrantedAuthority("USER"));
        if(admin)
            this.authorities.add(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AuthenticatedUser{" + "name=" + name + ", authorities=" + authorities + ", authenticated=" + authenticated + '}';
    }
}
