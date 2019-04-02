package com.nebula.font.check.service;

import com.nebula.font.check.ao.FontConditional;
import com.nebula.font.check.pojo.Font;

import java.util.List;

/**
 *  字体服务
 * @author chenjie
 * @date 2019/04/01
 **/

public interface FontService {

    void setFont(Font font);

    void updateFont(Font font);

    void deleteFontById(Integer id);

    /**
     * 分页查询
     */
    List<Font>  listFont(FontConditional conditions);
}
