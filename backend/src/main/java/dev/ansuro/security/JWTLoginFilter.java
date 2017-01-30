package dev.ansuro.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ansuro.rest.dto.CredentialsDTO;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Andy
 */
public final class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    
    private final TokenAuthenticationService tokenAuthenticationService;

    public JWTLoginFilter(String loginpath, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(loginpath));
        setAuthenticationManager(authenticationManager);
        tokenAuthenticationService = new TokenAuthenticationService();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        CredentialsDTO credentials = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String name = authResult.getName();
        tokenAuthenticationService.addAuthentication(response, name);
    }
}
