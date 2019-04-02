package com.nebula.font.check.adapter;

import com.nebula.font.check.model.data.FontData;
import com.nebula.font.check.model.po.FontPo;
import com.nebula.font.check.pojo.Font;
import com.nebula.font.check.service.FontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/***
 * @author chenjie
 * @date 2019/04/02
 **/
@Component
public class FontAdapter {

    @Autowired
    private FontService fontService;


    public FontData toFontData(Font fontPo) {
            FontData fontData = new FontData();
            fontData.setBaseFontCode(fontPo.getBaseFontCode());
            fontData.setCode(fontPo.getCode());
            fontData.setCompany(fontPo.getCompany());
            fontData.setDescription(fontPo.getDescription());
            fontData.setStatus(fontPo.getStatus());
            fontData.setName(fontPo.getName());

            return fontData;
    }

    public List<FontData> toListFontData(List<Font> fontPoList) {
        return fontPoList.stream().map(this::toFontData).collect(Collectors.toList());
    }



    public FontPo toFontPo(FontData fontData) {
        FontPo fontPo = new FontPo();
        fontPo.setBaseFontCode(fontData.getBaseFontCode());
        fontPo.setCode(fontData.getCode());
        fontPo.setDescription(fontData.getDescription());
        fontPo.setName(fontData.getName());
        fontPo.setCompany(fontData.getCompany());
        fontPo.setStatus(fontData.getStatus());

        return fontPo;
    }

}
