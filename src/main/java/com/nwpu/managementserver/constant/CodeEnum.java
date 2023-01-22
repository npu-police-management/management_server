package com.nwpu.managementserver.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Jiayi Zhu
 * 2023/1/22
 */
@AllArgsConstructor
@Getter
public enum CodeEnum {
    // ==================== 200 OK ====================
    Success(2000),

    // ==================== 400 BadRequest ====================
    RequestError(4000),

    CreationError(4001),

    // ==================== 401 Unauthorized ====================
    AccessTokenExpiredError(4010),

    RefreshTokenExpiredError(4011),

    UserUnauthenticated(4012),

    // ==================== 403 Forbidden ====================
    Forbidden(4030),

    // ==================== 404 NotFound ====================
    NotFound(4040),

    // ==================== 500 InternalServerError ====================
    ServerError(5000);

    private final int value;

    public HttpStatus getStatus() {

        int httpCode = value / 10;
        return HttpStatus.valueOf(httpCode);
    }

}
