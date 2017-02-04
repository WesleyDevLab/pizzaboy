package dev.ansuro.security;

import dev.ansuro.config.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andy
 */
@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private Logger log;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();
        log.debug(token);
        Jws<Claims> claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(Constants.JWT_SECRET)
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            //e.printStackTrace();
            return null;
        }
        String username = claims.getBody().getSubject();
        boolean admin = claims.getBody().get("admin", Boolean.class);
        log.debug("username: {}", username);
        return new AuthenticatedUser(username, admin);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthToken.class.isAssignableFrom(authentication);
    }

}
