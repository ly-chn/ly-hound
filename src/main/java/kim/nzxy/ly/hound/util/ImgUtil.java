package kim.nzxy.ly.hound.util;

import jiconfont.DefaultIconCode;
import jiconfont.IconCode;
import jiconfont.IconFont;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import java.io.InputStream;

/**
 * @author xy
 */
public class ImgUtil {
    static {
        IconFontSwing.register(new IconFont() {
            @Override
            public String getFontFamily() {
                return "IconFont";
            }

            @Override
            public InputStream getFontInputStream() {

                return ImgUtil.class.getResourceAsStream("/icons/iconfont.ttf");
            }
        });
    }

    public static Icon getIcon() {
        IconCode iconCode = new DefaultIconCode("IconFont", '\uE793');
        return IconFontSwing.buildIcon(iconCode, 16);
    }
}
