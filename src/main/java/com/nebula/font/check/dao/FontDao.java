package com.nebula.font.check.dao;

import com.nebula.font.check.ao.FontConditional;
import com.nebula.font.check.ao.Page;
import com.nebula.font.check.pojo.Font;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 对fun_font表进行操作
 * @author chenjie
 * @date 2019/04/01
 **/
@Mapper
public interface FontDao {

    void insert(Font font);

    void update(Font font);

    /**
     * 根据id进行删除
     */
    void deleteById(Integer id);

    /**
     *  分页查询（根据分页动态查询条件：字体类型列表，公司列表，字体编号列表）
     */
    List<Font> loadPageByConditional(@Param("conditions") FontConditional conditions);

    /**
     * 查询（测试使用）
     */
    Font loadFontById(Integer id);

    /**
     * 查询是否存在基础字
     */
    Font loadFontExistByCode(String code);



}
