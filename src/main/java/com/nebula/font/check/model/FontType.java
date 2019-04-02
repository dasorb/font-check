package com.nebula.font.check.model;


/***
 * 字体类型枚举类
 * @author chenjie
 * @date 2019/04/01
 **/
public enum FontType {

    C("彩色字", "color"), D("装饰字","deco"), M("彩色装饰字", "color_deco"), N("彩色拼音字","color_alpha"),
    T("繁体字", "tc"), B("基础字", "base");

    private String zhName;
    private String enName;

    private FontType(String zhName, String enName){
        this.zhName = zhName;
        this.enName = enName;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }
}
