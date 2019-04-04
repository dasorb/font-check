package com.nebula.font.check.model.conditions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 字体分页动态查询条件
 * @author chenjie
 * @date 2019/04/03
 **/
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class FontPageSearchItem {

    private List<String> fontTypes;       //字体类型列表
    private List<String> companies;        //公司列表
    private List<String> codes;               //字体编号列表

    private int currIndex = 1;              //开始页
    private int pageSize = 1;        //每页显示的数量
    private int total;                      //总数量
    private int totalPageNum;       //总页数

    /**
     * 根据总数量计算其他分页参数
     * @param total 总数量
     */
    public void build(int total) {
        this.total = total;
        this.currIndex = (getCurrIndex() - 1) * getPageSize();
        this.totalPageNum = 	 (int) ((total % this.pageSize == 0) ? total / pageSize : total / pageSize + 1);
    }

}
