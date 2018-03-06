package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    private static final String PATH = "images/"; // static part of image path.

    // gets BufferedImage by image file name, which store in resources directory.

    public static BufferedImage loadImage(String imageName){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(PATH + imageName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
