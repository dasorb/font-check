package com.nebula.font.check.model.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/***
 * font用于服务层的实体
 * @author chenjie
 * @date 2019/04/02
 **/
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FontData {
    private String name;        // 字体名称
    private String code;         // 字体编号
    private String type;          //字体类型
    private String description;         // 字体描述
    private String baseFontCode;    // 基础字编号
    private String company;            // 公司
    private int status;                       // 字体状态
    private long time;                         // 更新时间

    private int id;                             // id字段

}
