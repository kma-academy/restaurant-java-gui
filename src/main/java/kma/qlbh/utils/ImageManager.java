package kma.qlbh.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
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

    public void saveImage(BufferedImage bi, String name) throws IOException {
        String pathImages = getClass().getResource(imagesPath).getPath();
        File out = new File(pathImages + name + ".png");
        ImageIO.write(bi, "png", out);
    }
}
