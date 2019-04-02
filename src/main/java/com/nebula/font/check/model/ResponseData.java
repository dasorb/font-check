package com.nebula.font.check.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/***
 * @author chenjie
 * @date 2019/04/02
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResponseData {

    private int code;
    private String msg;
    private Object data;

}
