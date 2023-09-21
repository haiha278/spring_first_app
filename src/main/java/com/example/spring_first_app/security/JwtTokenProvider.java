package com.example.spring_first_app.security;

import com.example.spring_first_app.config.JwtConfigurationProperties;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {
    private final JwtConfigurationProperties jwtConfigurationProperties;
    private final long JWT_EXPIRATION = 604800000L;
    private final long JWT_REFRESH_EXPIRATION = 1209600000L;

    public String generateToken(CustomerDetails customerDetails) {
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + JWT_EXPIRATION);
        Map<String, Object> claim = new HashMap<>();
        claim.put("test", "ha");
        claim.put("test2", "ha");
        return Jwts.builder()
                .setSubject(Integer.toString(customerDetails.user.getId()))
                .setIssuedAt(now)
                .addClaims(claim)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, jwtConfigurationProperties.getSecret())
                .compact();
    }

    public boolean validateToken(String authToken) {
        try {
            var value = Jwts.parser().setSigningKey(jwtConfigurationProperties.getSecret()).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public Integer getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfigurationProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return Integer.parseInt(claims.getSubject());
    }

    public String generateRefreshToken(CustomerDetails customerDetails) {
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + JWT_REFRESH_EXPIRATION);
        Map<String, Object> claim = new HashMap<>();
        claim.put("test", "ha");
        claim.put("test2", "ha");
        return Jwts.builder()
                .setSubject(Integer.toString(customerDetails.user.getId()))
                .setIssuedAt(now)
                .addClaims(claim)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, jwtConfigurationProperties.getRefreshSecret())
                .compact();
    }

    public boolean validateRefreshToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtConfigurationProperties.getRefreshSecret()).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public Integer getUserIdFromJWTRefresh(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfigurationProperties.getRefreshSecret())
                .parseClaimsJws(token)
                .getBody();

        return Integer.parseInt(claims.getSubject());
    }
}
