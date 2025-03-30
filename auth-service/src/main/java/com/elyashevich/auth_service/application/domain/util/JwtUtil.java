package com.elyashevich.auth_service.application.domain.util;

import com.elyashevich.auth_service.application.domain.exception.InvalidTokenException;
import com.elyashevich.auth_service.application.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@UtilityClass
public class JwtUtil {

    @Value("${application.security.secret}")
    private String secret;

    public static String generateJwtToken(User user, Long tokenLifeTime) {
        var issuedAt = new Date();
        var expirationDate = new Date(issuedAt.getTime() + tokenLifeTime);

        return Jwts.builder()
            .setClaims(
                Map.of(
                    "roles",
                    user.getRoles()
                )
            )
            .setSubject(user.getEmail())
            .setIssuedAt(issuedAt)
            .setExpiration(expirationDate)
            .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
            .compact();
    }

    public static String validate(final String token) {
        if (getClaimsFromToken(token).getSubject().isEmpty()) {
            throw new InvalidTokenException("Invalid token.");
        }
        return token;
    }

    private static Claims getClaimsFromToken(final String token) {
        return Jwts
            .parserBuilder()
            .setSigningKeyResolver(new SigningKeyResolverAdapter() {
                @Override
                public byte[] resolveSigningKeyBytes(JwsHeader header, Claims claims) {
                    return secret.getBytes();
                }
            })
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
