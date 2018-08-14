package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Util class that loading images from directory,
 * and returns their as {@link BufferedImage}.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */

public class SpriteLoader {

    /**
     * Gets bufferedImage from file by filename.
     * @param name name of file.
     */

    public static BufferedImage load(String name){
        BufferedImage image = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            image = ImageIO.read(classLoader.getResource(name));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
