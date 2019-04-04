package com.nebula.font.check.configs;

import com.nebula.font.check.configs.constants.Code;

/***
 * @author chenjie
 * @date 2019/04/02
 **/
public class ResponseException extends Exception {

    private int errorCode;
    private String msg;

    public ResponseException(String msg) {
        super(msg);
        this.errorCode = Code.ERROR;
        this.msg = msg;
    }

    public ResponseException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
        this.msg = msg;
    }


}
