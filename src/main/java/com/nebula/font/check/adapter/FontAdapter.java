package com.nebula.font.check.adapter;

import com.nebula.font.check.model.data.FontData;
import com.nebula.font.check.model.po.FontPo;
import com.nebula.font.check.model.protocol.FontRequest;
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

    /**
     * 将表PO实体转换为service层使用的data类型
     *
     * @param fontPo fun_font Po实体
     * @return
     */
    public FontData toFontData(FontPo fontPo) {
            FontData fontData = new FontData();
            fontData.setBaseFontCode(fontPo.getBase_font_code());
            fontData.setCode(fontPo.getCode());
            fontData.setCompany(fontPo.getCompany());
            fontData.setDescription(fontPo.getDescription());
            fontData.setStatus(fontPo.getStatus());
            fontData.setName(fontPo.getName());
            fontData.setType(fontPo.getType());
            fontData.setTime(fontPo.getTime());
            fontData.setId(fontPo.getId());
            return fontData;
    }

    public FontData toFontData(FontRequest fontRequest) {
        FontData fontData = new FontData();
        fontData.setCode(fontRequest.getCode());
        fontData.setCompany(fontRequest.getCompany());
        fontData.setDescription(fontRequest.getDescription());
        fontData.setStatus(fontRequest.getStatus());
        fontData.setName(fontRequest.getName());
        fontData.setId(fontRequest.getId());
        return fontData;
    }

    public List<FontData> toListFontData(List<FontPo> fontPoList) {
        return fontPoList.stream().map(this::toFontData).collect(Collectors.toList());
    }



    public FontPo toFontPo(FontData fontData) {
        FontPo fontPo = new FontPo();
        fontPo.setBase_font_code(fontData.getBaseFontCode());
        fontPo.setCode(fontData.getCode());
        fontPo.setDescription(fontData.getDescription());
        fontPo.setName(fontData.getName());
        fontPo.setCompany(fontData.getCompany());
        fontPo.setStatus(fontData.getStatus());
        fontPo.setType(fontData.getType());
        fontPo.setTime(fontData.getTime());
        return fontPo;
    }

}
