package com.nwpu.managementserver.component;

import com.nwpu.managementserver.dto.TokenDTO;
import com.nwpu.managementserver.exception.JwtAuthException;
import com.nwpu.managementserver.service.RefreshTokenService;
import com.nwpu.managementserver.util.SnowflakeIdUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

import static com.nwpu.managementserver.constant.CodeEnum.*;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Component
public class JwtTokenProvider {

    @Value("${var.jwt.secret}")
    private String jwtSecret;

    @Value("${var.jwt.access-token-expiration}")
    private long jwtAccessExpirationInMs;

    @Value("${var.jwt.refresh-token-expiration}")
    private long jwtRefreshExpirationInMs;

    private RefreshTokenService refreshTokenService;

    @Autowired
    public void setRefreshTokenService(RefreshTokenService refreshTokenService) {

        this.refreshTokenService = refreshTokenService;
    }

    // generate token
    public TokenDTO generateToken(Authentication authentication) {

        String username = authentication.getName();

        String accessToken = generateAccessToken(username);
        String refreshToken = generateRefreshToken(username);

        return new TokenDTO(accessToken, refreshToken);
    }

    public TokenDTO generateToken(String refreshToken) {

        // get username from refreshToken
        String username = fromToken(refreshToken, Claims::getSubject);
        String accessToken = generateAccessToken(username);

        String newRefreshToken = requireRefresh(refreshToken) ?
                generateRefreshToken(username) : refreshToken;

        return new TokenDTO(accessToken, newRefreshToken);
    }

    public String generateAccessToken(String username) {

        Date currentTime = new Date();
        Date expireTime = new Date(currentTime.getTime() + jwtAccessExpirationInMs);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentTime)
                .setExpiration(expireTime)
                .signWith(getKey())
                .compact();
    }

    public String generateRefreshToken(String username) {

        Date currentTime = new Date();
        Date expireTime = new Date(currentTime.getTime() + jwtRefreshExpirationInMs);
        long id = SnowflakeIdUtil.nextId();

        String refreshToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentTime)
                .setExpiration(expireTime)
                .setId(String.valueOf(id))
                .signWith(getKey())
                .compact();
        refreshTokenService.saveRefreshToken(id, refreshToken);
        return refreshToken;
    }

    // get from the token
    public <T> T fromToken(String token, Function<Claims, T> func) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return func.apply(claims);
    }

    public boolean requireRefresh(String refreshToken) {

        Date expiration = fromToken(refreshToken, Claims::getExpiration);
        Date currentTime = new Date();

        // whether expiration < currentTime + 0.5*refreshExpiration
        // ensure refreshToken valid if login every refreshExpiration/2
        return expiration.before(new Date(currentTime.getTime() + jwtRefreshExpirationInMs/2));
    }

    // validate JWT token
    public void validateToken(String token) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
        }
        catch (SecurityException e){
            throw new JwtAuthException(RequestError, "Invalid JWT signature");
        } catch (MalformedJwtException e) {
            throw new JwtAuthException(RequestError, "Invalid JWT token");
        } catch (ExpiredJwtException e) {
            if (StringUtils.hasText(fromToken(token, Claims::getId))) {
                throw new JwtAuthException(AccessTokenExpiredError, "Expired JWT access-token");
            }
            else {
                throw new JwtAuthException(RefreshTokenExpiredError, "Expired JWT refresh-token");
            }
        } catch (UnsupportedJwtException e) {
            throw new JwtAuthException(RequestError, "Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new JwtAuthException(RequestError, "JWT claims string is empty.");
        }
    }

    /**
     * Check the refresh token is stored in the database
     */
    public void checkRefreshToken(String refreshToken) throws JwtAuthException {

        long id = Long.parseLong(fromToken(refreshToken, Claims::getId));
        String token = refreshTokenService.getRefreshTokenById(id);
        if (!refreshToken.equals(token)) {
            throw JwtAuthException.RefreshTokenNotMatch;
        }
    }

    private SecretKey getKey() {

        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
}
