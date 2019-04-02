package com.nebula.font.check.model.po;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * fun_font字体表PO
 * @author chenjie
 * @date 2019/4/2 0002 - 21:33
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class FontPo {

    private Integer id; // ID
    private String name; // 字体名称
    private String code; // 字体编号
    private String type; // 字体类型
    private String description; // 字体描述
    private String baseFontCode; // 基础字编号
    private String company; // 公司
    private int status; // 字体状态
    private Long time; // 更新时间

}
