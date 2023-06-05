package com.laarc.hoamanagerserver.api.module.security.utility;

import com.laarc.hoamanagerserver.shared.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final SecretKey secretKey;

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        String subject = userDetails.getUsername();
        String role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().get(0);

        return Jwts.builder()
                .setClaims(Map.of(
                        "subject", subject,
                        "role", role,
                        "iat", now
                ))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private JwtParser getJwtParser() {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();
    }

    private Jws<Claims> getClaims(String token) {
        token = token.replace("Bearer ", "");
        return getJwtParser().parseClaimsJws(token);
    }

    public String extractUsername(String token) {
        return  getClaims(token)
                .getBody()
                .get("subject").toString();
    }

    public GrantedAuthority extractUserAuthority(String token) {
        String roleName = getClaims(token)
                .getBody()
                .get("role").toString();
        return new SimpleGrantedAuthority(roleName);
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
