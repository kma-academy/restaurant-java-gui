package kma.qlbh.utils;

import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * @createAt Nov 15, 2020
 * @author Tran Duc Cuong<clonebmn2itt@gmail.com>
 */
public class ImageManager {

    public static String resourcesPath = "/";
    public static String imagesPath = resourcesPath + "images/";

    public ImageManager() {
    }

    public Icon getImage(String name) {
        try {
            URL pathImage = getClass().getResource(imagesPath + name);
            return new ImageIcon(pathImage);
        } catch (Exception e) {
            return null;
        }
    }
}
