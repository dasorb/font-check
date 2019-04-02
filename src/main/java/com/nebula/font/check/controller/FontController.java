package com.nebula.font.check.controller;

import com.nebula.font.check.ao.FontConditional;
import com.nebula.font.check.pojo.Font;
import com.nebula.font.check.pojo.ResponseException;
import com.nebula.font.check.service.FontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 对字体进行处理
 * @author chenjie
 * @date 2019/04/02
 **/
@RestController
public class FontController {

    @Autowired
    private FontService fontService;

    @PostMapping("/font")
    public Object create(Font font) throws Exception {
        validateFont(font);
        fontService.setFont(font);
        return font;
       //return ResponseMessage.success(font);
//
//
//        try {
//            //非空验证
//            if (! validateFont(font)) {
//                throw new IllegalArgumentException("插入的参数中，不能为空");
//            }
//            fontService.setFont(font);
//            return ResponseMessage.success(font);
//        } catch (IllegalArgumentException e) {
//            return ResponseMessage.error(e.getMessage())
//        }
    }

    @PutMapping("/font")
    public Map<String, Object> update(Font font){
        Map<String, Object> retMap = new HashMap<>();
        try {
            fontService.updateFont(font);
            retMap.put("code", "200");
            retMap.put("msg", "更新成功");
            retMap.put("data", font);
        } catch (IllegalArgumentException e) {
            retMap.put("code", "xxx");
            retMap.put("msg", e.getMessage());
        }

        return retMap;
    }

    @DeleteMapping("/font/{id}")
    public Map<String, Object> deleteById(@PathVariable("id") Integer id) {
        fontService.deleteFontById(1);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("code", "200");
        retMap.put("msg", "删除成功");
        retMap.put("data", "");

        return retMap;
    }

    @GetMapping("/font")
    public Map<String, Object> getFontsByPage(FontConditional fontConditional) {
        List<Font> fonts = fontService.listFont(fontConditional);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("code", "200");
        retMap.put("msg", "查询成功");
        retMap.put("data", fonts);

        return retMap;
    }

    /**
     * 验证非空
     * @param font
     * @return
     */
    private void validateFont(Font font) throws ResponseException {
        boolean flag = true;
        if (font.getCode() == null) {
            throw new ResponseException("字体编号不能为空");
        } else if (font.getName() == null) {
            throw new ResponseException("字体名称不能为空");
        } else if (font.getCompany() == null) {
            throw new ResponseException("公司不能为空");
        } else if (font.getDescription() == null) {
            throw new ResponseException("字体描述不能为空");
        } else if (font.getStatus() == null) {
            throw new ResponseException("字体状态不能为空");
        }
    }

}
