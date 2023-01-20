package com.nwpu.managementserver.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
public abstract class ManagementException extends RuntimeException {

    private final int code;

    public ManagementException(int code, String message) {

        super(message);
        this.code = code;
    }

    public abstract HttpStatus getStatus();
}
