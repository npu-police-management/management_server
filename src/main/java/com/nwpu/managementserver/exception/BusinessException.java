package com.nwpu.managementserver.exception;

import com.nwpu.managementserver.commen.ResultCodeEnum;

public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }


    public BusinessException(ResultCodeEnum resultCodeEnum, String message) {
        super(message);
        this.code = resultCodeEnum.getCode();
    }

    public int getCode() {
        return code;
    }


}
