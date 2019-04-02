package com.nebula.font.check.service.Impl;

import com.nebula.font.check.adapter.FontAdapter;
import com.nebula.font.check.ao.FontConditional;
import com.nebula.font.check.ao.FontType;
import com.nebula.font.check.dao.FontDao;
import com.nebula.font.check.model.data.FontData;
import com.nebula.font.check.pojo.Font;
import com.nebula.font.check.service.FontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/***
 * 对字体进行操作
 * @author chenjie
 * @date 2019/04/01
 **/
@Service
@Transactional
public class FontServiceImpl implements FontService {

    @Autowired
    private FontAdapter fontAdapter;
    @Autowired
    private FontDao fontDao;

    @Override
    public void setFont(Font font) {

        String fontCode = font.getCode();

        //判断输入字体编号为12.
        if(font.getCode().toCharArray().length != 12) {
            throw new IllegalArgumentException("字体编号必须要要求12位");
        }

        // 判断数据中字体编号最后一位是否属于字体类型
        boolean  lastCharIsCode= lastCharIsCode(font);
        if (lastCharIsCode) {
            // 校验正确，设置字体类型字段
            String fontType = getFontType(font);
            font.setType(fontType);
            font.setTime(System.currentTimeMillis());

            // 判断字体类型是基础字还是其他类型
            if(fontType.equalsIgnoreCase("B")) {
                // 是，则base_font_code为他自己本身
                font.setBaseFontCode(font.getCode());
            } else {
                // 其他类型，base_font_code为字体编号最后一位字符改为B,同时校验是否存在
                char[] chars = fontCode.toCharArray();
                chars[chars.length - 1] = 'B';
                String validateCodeString = chars.toString();
                // 校验是否存在
                boolean validateBaseFontExist = validateBaseFontExist(validateCodeString);
                if(! validateBaseFontExist) {
                    throw new IllegalArgumentException("该字体不存在基础字");
                }
                // 设置base_font_code
                font.setBaseFontCode(validateCodeString);
            }

            //新增该字体
            fontDao.insert(font);
        }

    }

    /**
     * 从字体编号中拿到字体类型
     */
    private String getFontType(Font font) {
        //拿到最后一个字符
        char[] chars = font.getCode().toCharArray();
        String lastCharacterString = String.valueOf(chars[chars.length - 1]);

        return lastCharacterString;
    }


    /**
     * 校验基础字是否存在
     */
    private boolean validateBaseFontExist(String fontCode) {
        if( fontDao.loadFontExistByCode(fontCode) != null ) {
            return true;
        }
        return false;
    }


    /**
     * 判断数据中字体编号最后一位是否属于字体类型
     */
    private boolean lastCharIsCode(Font font) {
        // 拿到最后一个字符
        String lastCharacterString = getFontType(font);

        // 遍历枚举类，是否输入枚举类中的任意类型
        for (FontType fontType : FontType.values()) {
            String fontTypeString = fontType.toString();
            if(fontTypeString.equalsIgnoreCase(lastCharacterString)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void updateFont(Font font) {

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

    }

    @Override
    public void deleteFontById(Integer id) {
        fontDao.deleteById(id);
    }

    @Override
    public List<FontData> listFont(FontConditional conditions) {
        // 设置当前页，对应数据库分页索引
        conditions.setCurrIndex(( conditions.getCurrIndex() - 1 )  * conditions.getPageSize());
        List<Font> poList = fontDao.loadPageByConditional(conditions);

        return  fontAdapter.toListFontData(poList);
    }

}
