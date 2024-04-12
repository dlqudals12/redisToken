package com.practiceProject.security;

import com.practiceProject.entity.User;
import com.practiceProject.security.model.CustomDetails;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Slf4j
public class JwtTokenProvider {
    private static final String JWT_SECRET = Base64.getEncoder().encodeToString(
            "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918b167a9c873fc4bb88c6976e5b5410415bde908bd4dee15dfa81f6f2ab448a918"
                    .getBytes());

    // 토큰 유효시간 24시간 (리프레시)
    private static final long JWT_EXPIRATION_REFRESH = 1000L * 60 * 60 * 14 * 24 * 20;
    // 토큰 유효시간 60분 (액세스)
    private static final long JWT_EXPIRATION_ACCESS = 1000 * 60;

    public static enum TokenType {
        REFRESH, ACCESS
    }

    ;

    // jwt 토큰 생성
    public static String generateToken(CustomDetails user, TokenType tokenType) {

        Date now = new Date();
        Date expiryDate = switch (tokenType) {
            case REFRESH -> new Date(now.getTime() + JWT_EXPIRATION_REFRESH);
            case ACCESS -> new Date(now.getTime() + JWT_EXPIRATION_ACCESS);
            default -> throw new UnsupportedJwtException("Token Type이 없습니다.");
        };

        Map<String, Object> claim = new HashMap<>();
        claim.put("user", user);

        return Jwts.builder()
                .setClaims(claim) // 사용자
                .setIssuedAt(new Date()) // 현재 시간 기반으로 생성
                .setExpiration(expiryDate) // 만료 시간 세팅
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET) // 사용할 암호화 알고리즘, signature에 들어갈 secret 값 세팅
                .compact();
    }

    // Jwt 토큰에서 아이디 추출
    public static Object getValueFromJWT(String token, String key) {
        String payload = token.split("[.]")[1];
        JacksonJsonParser parser = new JacksonJsonParser();
        Map map = parser.parseMap(new String(Base64.getDecoder().decode(payload)));
        return map.get(key);
    }

    // Jwt 토큰 유효성 검사
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
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

    public static String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (!ObjectUtils.isEmpty(bearerToken) &&
                (bearerToken.startsWith("Bearer ") || bearerToken.startsWith("bearer "))) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
}