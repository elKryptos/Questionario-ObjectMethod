package it.objectmethod.questionario2024.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.objectmethod.questionario2024.dto.UtenteDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(0)
public class BearerTokenUtil {

    private final SecretKey secretKey;

    public BearerTokenUtil(@Value("${private.key}") final String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Genera un nuovo token JWT basato sull'oggetto AuthorizationRequest.
     *
     * @param dto l'oggetto contenente le informazioni per la generazione del token
     * @return il token JWT generato
     */
    public String generateToken(final UtenteDto dto) {
        final Map<String, String> claims = new HashMap<>();
        claims.put("nome", dto.getNomeUtente());
        claims.put("isAdmin", dto.getIsAdmin() ? "1" : "0");
        claims.put("utenteId",dto.getUtenteId().toString());
        return Jwts.builder()
                .claims(claims)
                .subject(dto.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))  //durata =3h
                .signWith(secretKey)
                .compact();
    }


    /**
     * Valida un token JWT confrontando l'email estratta con il nome fornito nell'oggetto AuthorizationRequest
     * e verificando se il token è scaduto.
     *
     * @param jwtToken contains info about user's authorization.
     * @return true if token is valid, false if the token is not valid.
     */
    public Boolean isTokenValid(String jwtToken) {

        if (jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7).trim();
        }
        // Estrarre il token (rimuovendo il prefisso "Bearer ")
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
        Date expiration = claims.getExpiration();
        return expiration.after(new Date());
    }

    public Long getUtenteId(String jwtToken) {
        if (jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7).trim();
        }
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
        return Long.parseLong(claims.get("utenteId", String.class));
    }

    public String getNome(String jwtToken) {
        if (jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7).trim();
        }
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
        return claims.get("nome", String.class);
    }

    public String getSubject(String jwtToken) {
        if (jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7).trim();
        }
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
        return claims.getSubject();
    }
}
