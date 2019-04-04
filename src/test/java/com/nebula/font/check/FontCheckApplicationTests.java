package com.nebula.font.check;

import com.nebula.font.check.dao.FontDao;
import com.nebula.font.check.model.conditions.FontPageSearchItem;
import com.nebula.font.check.model.po.FontPo;
import com.nebula.font.check.service.FontService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FontCheckApplicationTests {

    @Autowired
    private FontDao fontDao;

    @Autowired
    private FontService fontService;


    /**
     * 初始化查询
     */
    @Test
    public void test1() {
        FontPo fontPo = fontDao.loadFontById(1);
        System.out.println(fontPo);
    }

    /**
     * 测试分页查询
     */
    @Test
    public void test2() {

        FontPageSearchItem fontPageSearchItem = new FontPageSearchItem();
        fontPageSearchItem.setCurrIndex(1);
        fontPageSearchItem.setPageSize(2);

        System.out.println(fontService.listPageFont(fontPageSearchItem));

    }

    @Test
    public void contextLoads() {

    }

}
