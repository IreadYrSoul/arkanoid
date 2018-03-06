package graphics;

import util.ImageLoader;

import java.awt.image.BufferedImage;

public class Textures {

    private static BufferedImage image; // basic image.

    public Textures(String imageName){
        image = ImageLoader.loadImage(imageName); // initializing basic image by image name.
    }

    // gets part of basic image with necessary sizes (width / height)
    // and location coordinates (x / y) on basic image.

    public static BufferedImage cut(int x, int y, int width, int height){
        return image.getSubimage(x, y, width, height);
    }

}
