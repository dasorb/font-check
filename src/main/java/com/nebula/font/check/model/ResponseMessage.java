package com.nebula.font.check.model;

/***
 * @author chenjie
 * @date 2019/04/02
 **/
public class ResponseMessage {

    public static ResponseData success(int code, String msg, Object data) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(code);
        responseData.setMsg(msg);
        responseData.setData(data);
        return responseData;
    }

    public static ResponseData success(Object data) {
        return success(Code.SUCCESS, "success", data);
    }

    public static ResponseData success(String msg, Object data) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(Code.SUCCESS);
        responseData.setMsg(msg);
        responseData.setData(data);
        return responseData;
    }

    public static ResponseData error(String msg) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(Code.ERROR);
        responseData.setMsg(msg);
        return responseData;
    }

    public static ResponseData error(int code, String msg) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(code);
        responseData.setMsg(msg);
        return responseData;
    }

}
