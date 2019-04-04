package com.nebula.font.check.configs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义异常处理类
 * @author chenjie
 * @date 2019/04/03
 **/
@ControllerAdvice
@ResponseBody
public class MyExceptionHandler {

    @ExceptionHandler(ResponseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handlerResponseException(ResponseException responseException) {
        return ResponseMessage.error(responseException.getMessage());
    }

}
