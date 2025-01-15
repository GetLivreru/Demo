package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class JWTUtil {

    private String secretKey = "yourSecretKey";  // Замените на свой секретный ключ
    private long validityInMilliseconds = 3600000; // 1 час

    // Генерация JWT токена
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Извлечение имени пользователя из токена
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Извлечение claims (например, имени пользователя)
    public <T> T extractClaim(String token, ClaimsResolver<T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.resolve(claims);
    }

    // Извлечение всех claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser()  // Обновлённый метод для версии 0.12.6
                .setSigningKey(secretKey)  // Устанавливаем ключ
                .build()  // Строим объект парсера
                .parseClaimsJws(token)     // Парсим JWS токен
                .getBody();                // Возвращаем тело токена
    }

    // Проверка токена на его валидность
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Извлечение даты истечения срока действия токена
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Проверка валидности токена
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // Интерфейс для получения claims
    public interface ClaimsResolver<T> {
        T resolve(Claims claims);
    }
}
