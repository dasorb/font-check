package com.nebula.font.check.adapter;

import com.nebula.font.check.model.data.FontData;
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


            return fontData;
    }

    public List<FontData> toListFontData(List<Font> fontPoList) {
        return fontPoList.stream().map(this::toFontData).collect(Collectors.toList());
    }



    public Font toFontPo(FontData fontData) {
        Font fontPo = new Font();

        return fontPo;
    }

}
