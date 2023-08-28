package com.betrybe.agrix.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.models.entities.Person;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * TokenService class.
 */
@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  /**
   * generateToken method.
   */
  public String generateToken(Person person) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
      .withIssuer("agrix")
      .withSubject(person.getUsername())
      .withExpiresAt(generateExpirationDate())
      .sign(algorithm);
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now()
      .plusHours(2)
      .toInstant(ZoneOffset.of("-03:00"));
  }

  /**
   * validateToken method.
   */
  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm)
      .withIssuer("agrix")
      .build()
      .verify(token)
      .getSubject();
  }

}
