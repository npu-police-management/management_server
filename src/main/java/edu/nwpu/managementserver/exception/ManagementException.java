package edu.nwpu.managementserver.exception;

import lombok.Getter;

/**
 * @author Jiayi Zhu
 * 2023/1/20
 */
@Getter
public abstract class ManagementException extends RuntimeException {


    public ManagementException(String message) {

        super(message);
    }

    public abstract int getCode();
}
