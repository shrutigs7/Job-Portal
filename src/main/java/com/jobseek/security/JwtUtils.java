
package com.jobseek.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    private final String jwtSecret = "my-super-secret-key-for-jwt-which-is-very-secure";
    private final long jwtExpirationMs = 86400000; // 1 day

    private final Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    /**
     * Generate JWT Token with authorities as claims
     */
    public String generateJwtToken(Authentication authentication) {
        String username = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        System.out.println("2 " + getAuthoritiesInString(authorities));

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .claim("authorities", getAuthoritiesInString(authorities)) // Add roles as claims
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Get username (email) from JWT token
     */
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    /**
     * Validate the JWT token
     */
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * Convert authorities to List<String> for storing in claims
     */
    private List<String> getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    /**
     * Convert List<String> back to List<GrantedAuthority> when parsing token
     */
    public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
        List<String> roles = claims.get("authorities", List.class);
        System.out.println("roles : " + roles);
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    public Claims getAllClaimsFromToken(String token) {
        System.out.println("Authorities from token: " + token);
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }


}
