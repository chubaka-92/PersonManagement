package com.example.personmanagement.config.securety.jwt;

import com.example.personmanagement.services.security.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtils {
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        log.info("Was calling generateJwtToken.");
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                //.signWith(jwtSecret, SignatureAlgorithm.HS512)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)// todo deprecated method
                //.signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")// todo deprecated method
                .compact();
    }

    public boolean validateJwtToken(String jwt) {
        log.info("Was calling validateJwtToken.");
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(jwt);// todo deprecated method  //  DONE
/*            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(jwt);*/
            return true;
        } catch (MalformedJwtException | IllegalArgumentException e) {
            log.error(e.getMessage());// todo нехорошо, используй логгер  //   DONE
        }
        return false;
    }

    public String getUserNameFromJwtToken(String jwt) {
        log.info("Was calling getUserNameFromJwtToken.");
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(jwt)
                .getBody().
                getSubject();// todo deprecated method, сделай перенос строки как в generateJwtToken
/*        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();*/
    }
}
