package dev.ansuro.config;

/**
 *
 * @author Andy
 */
public class Constants {
    public static final String JWT_HEADER = "Authorization";
    public static final long JWT_EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 10; // 10 days
    public static final String JWT_SECRET = "ThisIsASecret";
    public static final String JWT_PREFIX = "Bearer ";
}
