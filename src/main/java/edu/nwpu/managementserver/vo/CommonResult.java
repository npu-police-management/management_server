package edu.nwpu.managementserver.vo;

import edu.nwpu.managementserver.constant.CodeEnum;
import edu.nwpu.managementserver.exception.ManagementException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 返回模板
 * (其中success、code、message为必填，data为选填)
 */
@Slf4j
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResult implements Serializable {

    /**
     * 是否成功（必填）
     */
    private Boolean success;

    /**
     * 业务状态码（必填）
     */
    private Integer code;

    /**
     * 对业务状态码的解释（必填）
     */
    private String msg;

    /**
     * 返回数据（选填）
     */
    private Object data;

    private CommonResult(boolean success, CodeEnum code, String msg, Object data) {

        this.success = success;
        this.code = code.getValue();
        this.msg = msg;
        this.data = data;
    }


    public static CommonResult success(Object data) {

        return new CommonResult(true, CodeEnum.Success, "操作成功", data);
    }

    public static CommonResult success() {

        return CommonResult.success(null);
    }

    public static CommonResult failure(CodeEnum code, String msg) {

        return new CommonResult(false, code, msg, null);
    }

    public static CommonResult failure(ManagementException e) {

        return new CommonResult(false, e.getCode(), e.getMessage(), null);
    }

    public static CommonResult badRequest(CodeEnum code, String msg) {

        if (!HttpStatus.BAD_REQUEST.equals(code.getStatus())) {
            log.warn("错误代码 {} 与方法不匹配", code);
        }
        return CommonResult.failure(code, msg);
    }

    public static CommonResult unauthorized(CodeEnum code, String msg) {

        if (!HttpStatus.UNAUTHORIZED.equals(code.getStatus())) {
            log.warn("错误代码 {} 与方法不匹配", code);
        }
        return CommonResult.failure(code, msg);
    }

    public static CommonResult forbidden(CodeEnum code, String msg) {

        if (!HttpStatus.FORBIDDEN.equals(code.getStatus())) {
            log.warn("错误代码 {} 与方法不匹配", code);
        }
        return CommonResult.failure(code, msg);
    }

    public static CommonResult notFound(CodeEnum code, String msg) {

        if (!HttpStatus.NOT_FOUND.equals(code.getStatus())) {
            log.warn("错误代码 {} 与方法不匹配", code);
        }
        return CommonResult.failure(code, msg);
    }

    public static CommonResult internalServerError(CodeEnum code, String msg) {

        if (!HttpStatus.INTERNAL_SERVER_ERROR.equals(code.getStatus())) {
            log.warn("错误代码 {} 与方法不匹配", code);
        }
        return CommonResult.failure(code, msg);
    }







//    public static CommonResult success() {
//        return new CommonResult(true, 200, "操作成功", null);
//    }
//
//    public static CommonResult success(Object data) {
//        return new CommonResult(true, 200, "操作成功", data);
//    }
//
//    public static CommonResult failure(String message) {
//        return new CommonResult(false, -1, message, null);
//    }
//
//    public static CommonResult failure(Integer code, String message) {
//        return new CommonResult(false, code, message, null);
//    }
//
//    public static CommonResult forbidden(String message) {
//        return new CommonResult(false, 403, message, null);
//    }
//
//    public static CommonResult forbidden(Integer code, String message) {
//        return new CommonResult(false, code, message, null);
//    }
//
//    public static CommonResult unauthorized(String message) {
//        return new CommonResult(false, 401, message, null);
//    }
}