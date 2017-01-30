package dev.ansuro.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

/**
 *
 * @author Andy
 */
public class TokenAuthenticationService {
    private final static long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; // 10 days
    private final static String SECRET = "ThisIsASecret";
    private final static String PREFIX = "Bearer";
    private final static String HEADER = "Authorization";
    
    public void addAuthentication(HttpServletResponse response, String username) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        
        response.addHeader(HEADER, PREFIX + " " + jwt);
    }
    
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER);
        if(token != null) {
            String username = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            
            if(username != null)
                return new AuthenticatedUser(username);
        }
        
        return null;
    }
}
