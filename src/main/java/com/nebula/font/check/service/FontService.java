package com.nebula.font.check.service;

import com.nebula.font.check.adapter.FontAdapter;
import com.nebula.font.check.model.conditions.FontPageSearchItem;
import com.nebula.font.check.model.enums.FontTypeEnum;
import com.nebula.font.check.dao.FontDao;
import com.nebula.font.check.configs.ResponseException;
import com.nebula.font.check.model.data.FontData;
import com.nebula.font.check.model.po.FontPo;
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
     * 插入或者修改数据
     * @param fontData
     */
    public void createOrUpdateFont(FontData fontData) throws ResponseException {
        //校验字体长度是否是12位
        validateFontCodeLength(fontData);
        //校验字体是否属于字体类型
        validateFontCodeFromType(fontData);
        // 判断字体类型是其他类型还是基础类型,设置basefontcode
        String fontType = getFontType(fontData);
        if(fontType.equalsIgnoreCase("B")) {
            // 基础字体逻辑：将字体编号设置为基础字
            fontData.setBaseFontCode(fontData.getCode()); //
        } else {
            // 其他字体逻辑：校验是否存在基础字，如果存在，字体编号末位转换为B设置基础字
            validateBaseFontExist(fontType);
            fontData.setBaseFontCode(fontCodeLastCharToB(fontData));
        }
        //设置更新时间，和根据字   体编号设置类型
        fontData.setType(getFontType(fontData));
        fontData.setTime(System.currentTimeMillis());

        // 根据ID是否有值判断时添加还是修改，有：修改，无：添加
        if(fontData.getId() == 0) {
            //添加
            //校验字体是否存在于数据库
            validateFontCodeByCode(fontData);
            insetFont(fontData);
        } else {
            // 修改
            // 先根据id查询到数据，将入参转换为po实体，进行更新
            FontPo fontPo = fontDao.loadFontById(fontData.getId());
            // 将查询到的PO实体，更新为从前台传入的参数
            fontPo.setType(fontData.getType());
            fontPo.setBase_font_code(fontData.getBaseFontCode());
            fontPo.setName(fontData.getName());
            fontPo.setDescription(fontData.getDescription());
            fontPo.setStatus(fontData.getStatus());
            fontPo.setCompany(fontData.getCompany());
            fontPo.setTime(fontData.getTime());
            fontPo.setCode(fontData.getCode());
            //执行更新
            fontDao.update(fontPo);
        }
    }

    /**
     * 插入数据
     * @param fontData 字体数据
     */
    @Transactional
    public void setFont(FontData fontData) throws ResponseException {

        // 校验字体是否已经存在数据库
        // validateFontCodeByCode(fontData);

        //校验字体编号长度是否为12
        validateFontCodeLength(fontData);

        //校验字体是否属于字体类型
        validateFontCodeFromType(fontData);

        // 判断字体类型是其他类型还是基础类型
        String fontType = getFontType(fontData);
        if(fontType.equalsIgnoreCase("B")) {
            // 基础字体逻辑：将字体编号设置为基础字
            fontData.setBaseFontCode(fontData.getCode()); //
        } else {
            // 其他字体逻辑：校验是否存在基础字，如果存在，字体编号末位转换为B设置基础字
            validateBaseFontExist(fontType);
            fontData.setBaseFontCode(fontCodeLastCharToB(fontData));
        }

        insetFont(fontData);
    }

    /**
     * 修改数据
     *
     * @param fontData 修改数据
     * @throws ResponseException
     */
    @Transactional
    public void updateFont(FontData fontData) throws ResponseException {

        // 根据字体编号，验证该字体是否存在于数据库
        // validateFontCodeByCode(fontData);

        //进行更新操作,先根据id查询到数据，对fontType进行赋值，转换为po实体，进行更新
        FontPo fontPo = fontDao.loadFontById(fontData.getId());
        fontData.setType(getFontType(fontData));
        FontPo updateFontPo = fontAdapter.toFontPo(fontData);

        fontPo.setType(updateFontPo.getType());
        fontPo.setBase_font_code(updateFontPo.getBase_font_code());
        fontPo.setName(updateFontPo.getName());
        fontPo.setDescription(updateFontPo.getDescription());
        fontPo.setStatus(updateFontPo.getStatus());
        fontPo.setCompany(updateFontPo.getCompany());
        fontPo.setTime(System.currentTimeMillis());
        fontPo.setCode(updateFontPo.getCode());
        //执行更新
        fontDao.update(fontPo);
    }

    /**
     * 根据ID删除数据
     *
     * @param id id
     */
    public void deleteFontById(int id) throws ResponseException {
        FontPo fontPo = fontDao.loadFontById(id);
        if(fontPo == null) {
            throw  new ResponseException("删除失败，对象在数据库中不存在！");
        }
        fontDao.deleteById(id);
    }

    /**
     *  根据fontpageSearchItem中的条件进行分页东岱查询
     * @param fontPageSearchItem 条件
     * @return
     */
    public List<FontData> listPageFont(FontPageSearchItem fontPageSearchItem) {
        // 设置分页条件，并查询
        fontPageSearchItem.build(fontDao.loadTotalCountNum());
        List<FontPo> fonts = fontDao.loadPageByConditional(fontPageSearchItem);
        // 转换为fontdata数据并返回
        return fontAdapter.toListFontData(fonts);
    }

    /**
     * 转换为表Po实体并插入数据
     */
    private void insetFont(FontData fontData) {
        FontPo fontPo = fontAdapter.toFontPo(fontData);
        // 设置时间，根据字体编号设置类型
        fontPo.setTime(System.currentTimeMillis());
        fontPo.setType(getFontType(fontData));
        // 插入数据
        fontDao.insert(fontPo);
    }

    /**
     *  校验字体是否存在于数据库中
     */
    private void validateFontCodeByCode(FontData fontData) throws ResponseException {
        //根据字体编码从数据库查询的po实体
        FontPo fontPo = fontDao.loadFontExistByCode(fontData.getCode());
        if(fontPo != null) {
            throw new ResponseException("字体已经存在于数据库中");
        }
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
        for (FontTypeEnum fontTypeEnum : FontTypeEnum.values()) {
            String fontTypeString = fontTypeEnum.toString();
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
     *  字体编号最后一位转换为B返回字体编号
     */
    private String fontCodeLastCharToB(FontData fontData) {
        char[] chars = fontData.getCode().toCharArray();
        chars[chars.length - 1]  = 'B';
        return String.valueOf(chars);
    }


}
