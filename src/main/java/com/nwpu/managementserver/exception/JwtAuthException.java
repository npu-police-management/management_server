package com.nwpu.managementserver.exception;

import com.nwpu.managementserver.constant.CodeEnum;

import static com.nwpu.managementserver.constant.CodeEnum.*;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
public class JwtAuthException extends ManagementException {

    public static JwtAuthException RefreshTokenNotFound
            = new JwtAuthException(UserUnauthenticated, "无效的 Refresh Token, 需重新登陆");

    public static JwtAuthException RefreshTokenNotMatch
            = new JwtAuthException(RequestError, "Refresh Token Not Match");

    private final CodeEnum code;

    //private final HttpStatus status;
    
    public JwtAuthException(CodeEnum code, String message) {
        
        super(message);
        this.code = code;
    }

    @Override
    public int getCode() {

        return code.getValue();
    }
}
