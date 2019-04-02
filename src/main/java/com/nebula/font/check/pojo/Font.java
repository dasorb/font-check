package com.nebula.font.check.pojo;

/***
 * 字体
 * @author chenjie
 * @date 2019/04/01
 **/
public class Font {

    private Integer id; // ID
    private String name; // 字体名称
    private String code; // 字体编号
    private String type; // 字体类型
    private String description; // 字体描述
    private String baseFontCode; // 基础字编号
    private String company; // 公司
    private Integer status; // 字体状态
    private Long time; // 更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBaseFontCode() {
        return baseFontCode;
    }

    public void setBaseFontCode(String baseFontCode) {
        this.baseFontCode = baseFontCode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Font{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", baseFontCode='" + baseFontCode + '\'' +
                ", company='" + company + '\'' +
                ", status=" + status +
                ", time=" + time +
                '}';
    }
}
