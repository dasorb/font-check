package com.nebula.font.check.controller;

import com.nebula.font.check.adapter.FontAdapter;
import com.nebula.font.check.configs.ResponseException;
import com.nebula.font.check.model.conditions.FontPageSearchItem;
import com.nebula.font.check.model.data.FontData;
import com.nebula.font.check.model.protocol.FontRequest;
import com.nebula.font.check.service.FontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * 对字体进行处理的协议
 * @author chenjie
 * @date 2019/04/02
 **/
@RequestMapping("/font")
@RestController
public class FontController {

    @Autowired
    private FontAdapter fontAdapter;
    @Autowired
    private FontService fontService;

    @PostMapping(value = {"/save", "/update"})
    public Object updateOrSave(FontRequest fontRequest) throws ResponseException {
        // 非空检查
        fontRequest.check();
        // 将数据适配为服务层使用数据
        FontData fontData = fontAdapter.toFontData(fontRequest);
        //插入数据或修改数据
        fontService.createOrUpdateFont(fontData);
        return "success";
    }

    /**
     * 插入保存一条数据
     *
     * @param fontRequest 请求接受参数
     * @return ""
     * @throws Exception
     */
    /*@PostMapping("/save")
    public Object save(FontRequest fontRequest) throws Exception {
        // 非空验证
        fontRequest.check();
        // 转换数据为service层时用的数据格式
        FontData fontData = fontAdapter.toFontData(fontRequest);
        // 插入数据
        fontService.setFont(fontData);
        return fontData;
    }*/

    /**
     *  修改数据
     *
     * @param fontRequest 请求传来的参数
     * @return
     */
    /*@PutMapping("/update")
    public Object update(FontRequest fontRequest) throws Exception {
        // 非空验证
        fontRequest.check();;
        // 转换为service层使用数据格式
        FontData fontData = fontAdapter.toFontData(fontRequest);
        // 修改数据
        fontService.updateFont(fontData);
        return fontData;
    }*/

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Object deleteById(@PathVariable("id") int id) throws ResponseException {
        fontService.deleteFontById(id);
        return "success";
    }

    /**
     * 分页动态条件查询
     *
     * @param fontPageSearchItem 分页参数+查询条件
     * @return
     */
    @GetMapping("/search")
    public Object searchByConditional(FontPageSearchItem fontPageSearchItem) {
        List<FontData> fontData = fontService.listPageFont(fontPageSearchItem);
        return fontData;
    }

}
