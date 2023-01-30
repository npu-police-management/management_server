package edu.nwpu.managementserver.exception;

import edu.nwpu.managementserver.constant.CodeEnum;

/**
 * 处理service层各种异常
 */
public class BusinessException extends ManagementException {

    private final int code;

    public BusinessException(CodeEnum code, String message) {
        super(message);
        this.code = code.getValue();
    }


    public int getCode() {
        return code;
    }

}
