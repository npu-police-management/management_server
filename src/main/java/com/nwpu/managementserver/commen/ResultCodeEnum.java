package com.nwpu.managementserver.commen;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    PARAM_VALIDATE_FAILED(1001, "参数校验失败");

    private final int code;
    private final String message;

    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
