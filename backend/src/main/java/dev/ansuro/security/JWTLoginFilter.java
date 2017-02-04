package dev.ansuro.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ansuro.config.Constants;
import dev.ansuro.rest.dto.CredentialsDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

/**
 *
 * @author Andy
 */
public final class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter() {
        super("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        CredentialsDTO credentials = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        final String name = authResult.getName();
        final boolean admin = authResult.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ADMIN"));
        final String jwt = Jwts.builder()
                .setSubject(name)
                .claim("admin", admin)
                .setExpiration(new Date(System.currentTimeMillis() + Constants.JWT_EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, Constants.JWT_SECRET)
                .compact();
        
        response.addHeader(Constants.JWT_HEADER, Constants.JWT_PREFIX + jwt);
    }
}
