package com.nebula.font.check.dao;

import com.nebula.font.check.model.conditions.FontPageSearchItem;
import com.nebula.font.check.model.po.FontPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 对fun_font表进行操作
 * @author chenjie
 * @date 2019/04/01
 **/
@Mapper
public interface FontDao {

    void insert(FontPo fontPo);

    void update(FontPo fontPo);

    /**
     * 根据id删除
     * @param id ID
     */
    void deleteById(Integer id);

    /**
     * 分页动态查询（根据分页动态查询条件：字体类型列表，公司列表，字体编号列表）
     *
     * @param conditions 条件
     * @return
     */
    List<FontPo> loadPageByConditional(@Param("conditions") FontPageSearchItem conditions);

    /**
     * 查询（测试使用）
     *
     * @param id ID
     * @return
     */
    FontPo loadFontById(int id);

    /**
     * 查询是否存在基础字
     *
     * @param code 查询是否存在字体编号
     * @return
     */
    FontPo loadFontExistByCode(String code);

    /**
     * 查询表中一共有多少条数据
     * @return 总数量
     */
    int loadTotalCountNum();



}
