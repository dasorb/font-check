package com.nebula.font.check.model;

import java.util.List;

/***
 * @author chenjie
 * @date 2019/04/01
 **/
public class FontConditional {

    private List<String> fontTypeConditionalList; //字体类型列表

    private List<String> companyConditionalList; //公司列表

    private List<String> codeConditionalList; //字体

    private Integer currIndex; //当前开始

    private Integer pageSize; //显示数量

    public List<String> getFontTypeConditionalList() {
        return fontTypeConditionalList;
    }

    public void setFontTypeConditionalList(List<String> fontTypeConditionalList) {
        this.fontTypeConditionalList = fontTypeConditionalList;
    }

    public List<String> getCompanyConditionalList() {
        return companyConditionalList;
    }

    public void setCompanyConditionalList(List<String> companyConditionalList) {
        this.companyConditionalList = companyConditionalList;
    }

    public List<String> getCodeConditionalList() {
        return codeConditionalList;
    }

    public void setCodeConditionalList(List<String> codeConditionalList) {
        this.codeConditionalList = codeConditionalList;
    }

    public Integer getCurrIndex() {
        return currIndex;
    }

    public void setCurrIndex(Integer currIndex) {
        this.currIndex = currIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
