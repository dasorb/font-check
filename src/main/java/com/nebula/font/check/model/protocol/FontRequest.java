package com.nebula.font.check.model.protocol;

import com.nebula.font.check.configs.ResponseException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/***
 * @author chenjie
 * @date 2019/04/03
 **/
@Getter
@Setter
@ToString
public class FontRequest {

    private String name;        // 字体名称
    private String code;         // 字体编号
    private String description;         // 字体描述
    private String company;            // 公司
    private int status;                       // 字体状态
    private int id;                             // ID


    public void check() throws ResponseException {
        if (this.code == null) {
            throw new ResponseException("字体编号不能为空！");
        }
        else if (this.company == null) {
            throw new ResponseException("公司名称不能为空！");
        }
        else if (this.description == null) {
            throw new ResponseException("字体描述不能为空！");
        }
        else if (this.name == null) {
            throw  new ResponseException("字体名称不能为空！");
        }
    }

}
