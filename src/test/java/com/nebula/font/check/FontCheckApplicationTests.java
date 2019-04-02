package com.nebula.font.check;

import com.nebula.font.check.model.FontConditional;
import com.nebula.font.check.dao.FontDao;
import com.nebula.font.check.pojo.Font;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FontCheckApplicationTests {

    @Autowired
    private FontDao fontDao;

    @Autowired
    private FontService fontService;

    @Test
    public void test1() {
        //测试新增字体
        Font font = fontDao.loadFontById(3);
        font.setName("楷");
        font.setCode("A010331RL02C");
    }

    @Test
    public void test2() {
        // 测试update
        Font font = fontDao.loadFontById(1);
        font.setName("测");
        fontService.updateFont(font);
    }

    @Test
    public void contextLoads() {

        //测试条件查询
        FontConditional fontConditional = new FontConditional();
        List<String> fontType = new ArrayList<>();
        fontType.add("C");
        fontConditional.setFontTypeConditionalList(fontType);

        fontConditional.setCurrIndex(0);
        fontConditional.setPageSize(1);


        List<Font> fonts = fontDao.loadPageByConditional(fontConditional);

        System.out.println(fonts);

        //Font font = fontDao.loadFontById(1);
        //System.out.println(font);
    }

}
