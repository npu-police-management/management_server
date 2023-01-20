package com.nwpu.managementserver.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
public class JwtAuthException extends ManagementException {

    public static JwtAuthException RefreshTokenNotFound
            = new JwtAuthException(HttpStatus.BAD_REQUEST, "Refresh Token Not Found");

    public static JwtAuthException RefreshTokenNotMatch
            = new JwtAuthException(HttpStatus.BAD_REQUEST, "Refresh Token Not Match");

    public static JwtAuthException RefreshTokenExpired
            = new JwtAuthException(HttpStatus.BAD_REQUEST, "Refresh Token is Expired");

    private final HttpStatus status;
    
    public JwtAuthException(HttpStatus status, String message) {
        
        super(status.value(), message);
        this.status = status;
    }

    @Override
    public HttpStatus getStatus() {

        return status;
    }
}
