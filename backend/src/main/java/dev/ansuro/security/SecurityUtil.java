package dev.ansuro.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Andy
 */
public class SecurityUtil {
// TODO test
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof AuthenticatedUser) {
                return authentication.isAuthenticated();
            }
        }

        return false;
    }

    public static AuthenticatedUser getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            if (authentication instanceof AuthenticatedUser) {
                return (AuthenticatedUser) authentication;
            }
        }

        throw new IllegalStateException("User not found!");
    }
    
    public static String getCurrentUsername() {
        return SecurityUtil.getCurrentUser().getName();
    }

}
