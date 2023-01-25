package com.nwpu.managementserver.commen;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwpu.managementserver.exception.ManagementException;
import com.nwpu.managementserver.vo.CommonResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

import static com.nwpu.managementserver.constant.CodeEnum.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {

        this.objectMapper = objectMapper;
    }

    /**
     * 参数校验失败的异常处理器
     * 对于request中body部分的校验，校验失败抛出的异常是MethodArgumentNotValidException，但是我们捕获的不是MethodArgumentNotValidException
     * 因为BindException是MethodArgumentNotValidException的父类，
     * 所以这里捕获BindException不仅可以顺带捕获它的子类MethodArgumentNotValidException，还可以捕获request中query参数的校验失败，相当于一个方法处理了多种不同类型的参数校验失败
     */
    @ExceptionHandler(BindException.class)
    public CommonResult handleBindException(BindException e, HttpServletRequest request) {
        String message = formatBindException(e);
        log.warn(formatException(e, request, message, false));
        return CommonResult.badRequest(RequestError, message);
    }


    /**
     * 请求方式不支持
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult handleMethodNotAllowed(Exception e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, false));
        return CommonResult.badRequest(RequestError, "请求方式不支持");
    }


    /**
     * 请求格式不对
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ServletRequestBindingException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public CommonResult handleBadRequest(Exception e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, false));
        return CommonResult.badRequest(RequestError, "请求格式不对");
    }

    /**
     * 请求URL有误，无法解析这个URL该对应Controller中哪个方法
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public CommonResult handleNotFoundException(NoHandlerFoundException e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, false));
        return CommonResult.notFound(NotFound, "请求URL不存在");
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, false));
        return CommonResult.forbidden(Forbidden, e.getMessage());
    }

    /**
     * 参数校验失败异常处理
     * */
    @ExceptionHandler(ValidationException.class)
    public CommonResult handleValidationException(ValidationException e) {
        log.warn("参数校验出错，错误信息：{}", e.getMessage());
        String msg = e.getMessage();
        msg = msg.substring(msg.indexOf(".") + 1);
        return CommonResult.badRequest(RequestError, msg);
    }

    /**
     * 参数校验异常处理
     * */
    private String validation(BindingResult bindingResult) {
        List<ObjectError> errors = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder();
        if (!CollectionUtils.isEmpty(errors)) {
            for (ObjectError error : errors) {
                FieldError fieldError = (FieldError) error;
                sb
                        .append(fieldError.getField())
                        .append(fieldError.getDefaultMessage())
                        .append(",");
            }
        }
        return sb.length()>0?sb.substring(0,sb.length()-1):sb.toString();
    }

    /**
     * 业务异常，可细分为多种情况，可见ResultCodeEnum
     */
    @ExceptionHandler(ManagementException.class)
    public CommonResult handleBusinessException(ManagementException e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, true));
        return CommonResult.failure(e);
    }


    /**
     * 如果前面的处理器都没拦截住，最后兜底
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, true));
//        e.printStackTrace();
        return CommonResult.internalServerError(ServerError, "服务器内部错误");
    }


    /**
     * 把异常信息格式化成自己喜欢的格式，这个方法用于格式化Exception
     */
    @SneakyThrows
    public String formatException(Exception e, HttpServletRequest request, String message, boolean stackRequired) {
        StringBuilder sb = new StringBuilder();
        sb.append("(异常)")
                .append("<类型>").append(e.getClass())
                .append("<信息>").append(message != null ? message : e.getMessage());
        if (stackRequired) {
            sb.append("<堆栈>").append(objectMapper.writeValueAsString(e));
        }
        return sb.toString();
    }


    /**
     * 把异常信息格式化成自己喜欢的格式，这个方法用于格式化BindException
     */
    public String formatBindException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError error : fieldErrors) {
            //提示：error.getField()得到的是校验失败的字段名字，error.getDefaultMessage()得到的是校验失败的原因
            sb.append(error.getField())
                    .append("=[")
                    .append(error.getDefaultMessage())
                    .append("]  ");
        }
        return sb.toString();

    }
}
