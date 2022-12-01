package kim.nzxy.ly.hound.util;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;
import java.net.URL;
import java.util.Optional;

/**
 * @author xy
 */
public class ImgUtil {
    public static ImageIcon of(String path) {
        Icon icon = IconLoader.findIcon(path);
        URL url = ImgUtil.class.getResource(path);
        if (url == null) {
            throw new RuntimeException();
        }
        return new ImageIcon(url);
    }
}
