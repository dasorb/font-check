package com.nebula.font.check.service;

import com.nebula.font.check.adapter.FontAdapter;
import com.nebula.font.check.model.FontConditional;
import com.nebula.font.check.model.FontType;
import com.nebula.font.check.dao.FontDao;
import com.nebula.font.check.model.ResponseException;
import com.nebula.font.check.model.data.FontData;
import com.nebula.font.check.model.po.FontPo;
import com.nebula.font.check.pojo.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * 对字体进行操作
 * @author chenjie
 * @date 2019/04/01
 **/
@Service
@Transactional
public class FontService {

    @Autowired
    private FontAdapter fontAdapter;

    @Autowired
    private FontDao fontDao;

    /**
     * 插入数据
     * @param fontData 字体数据
     */
    public void setFont(FontData fontData) throws ResponseException {

        //判断字体编号长度是否为12
        validateFontCodeLength(fontData);

        //校验字体是否属于字体类型
        validateFontCodeFromType(fontData);

        // 判断字体类型是其他类型还是基础类型
        String fontType = getFontType(fontData);
        if(fontType.equalsIgnoreCase("B")) {
            // 基础字体逻辑
            fontData.setBaseFontCode(fontData.getCode());
        } else {
            // 其他字体逻辑：校验是否存在基础字
            validateBaseFontExist(fontType);
            fontData.setBaseFontCode(fontCodeLastCharToB(fontData));
        }

        insetFont(fontData);
    }

    /**
     * 插入字体
     */
    private void insetFont(FontData fontData) {
        FontPo fontPo = fontAdapter.toFontPo(fontData);
        fontPo.setTime(System.currentTimeMillis());
        fontPo.setType(getFontType(fontData));
        fontDao.insert(fontPo);
    }

    /**
     *  校验字体编号等于12位
     */
    private void validateFontCodeLength(FontData fontData) throws ResponseException {
        if (fontData.getCode().length() != 12) {
            throw new ResponseException("字体编号不是12位");
        }
    }

    /**
     *  校验字体编号最后一位是否属于字体类型
     */
    private void validateFontCodeFromType(FontData fontData) throws ResponseException {
        // 在字体编号中拿到最后一位
        String validateCode = getFontType(fontData);
        boolean flag = false;
        // 遍历枚举类，是否输入枚举类中的任意类型
        for (FontType fontType : FontType.values()) {
            String fontTypeString = fontType.toString();
            if (validateCode.equalsIgnoreCase(fontTypeString)) {
                flag = true;
            }
        }
        // 如果没有在枚举中找到，则校验失败
        if(! flag) {
            throw new ResponseException("该字体不属于任何字体类型");
        }
    }

    /**
     * 校验基础字是否存在
     */
    private void validateBaseFontExist(String fontCode) throws ResponseException {
        if( fontDao.loadFontExistByCode(fontCode) == null ) {
            throw new ResponseException("改字体不存在基础字类型");
        }
    }

    /**
     * 从字体编号中拿到最后一位
     */
    private String getFontType(FontData fontData) {
        //拿到最后一个字符
        char[] chars = fontData.getCode().toCharArray();
        String lastCharacterString = String.valueOf(chars[chars.length - 1]);
        return lastCharacterString;
    }

    /**
     *  字体编号最后一位 to b
     */
    private String fontCodeLastCharToB(FontData fontData) {
        char[] chars = fontData.getCode().toCharArray();
        chars[chars.length - 1]  = 'B';
        return String.valueOf(chars);
    }

    /*public void updateFont(Font font) {

        //判断输入字体编号为12.
        if(font.getCode().toCharArray().length != 12) {
            throw new IllegalArgumentException("字体编号必须要要求12位");
        }

        //校验字体编号最后一位表示字体类型
        if (! lastCharIsCode(font)) {
            throw new IllegalArgumentException("字体编号最后一位不属于字体类型");
        }

        // 校验base基础码存在
        if (! validateBaseFontExist(font.getBaseFontCode())) {
            throw new IllegalArgumentException("字体基础字不存在");
        }

        // 设置更新时间
        font.setTime(System.currentTimeMillis());

        fontDao.update(font);

    }*/

    public void deleteFontById(Integer id) {
        fontDao.deleteById(id);
    }

    public List<FontData> listFont(FontConditional conditions) {
        // 设置当前页，对应数据库分页索引
        conditions.setCurrIndex(( conditions.getCurrIndex() - 1 )  * conditions.getPageSize());
        List<Font> poList = fontDao.loadPageByConditional(conditions);

        return  fontAdapter.toListFontData(poList);
    }

}
