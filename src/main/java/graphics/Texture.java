package graphics;

import util.ImageLoader;

import java.awt.image.BufferedImage;

public class Texture {

    private static BufferedImage image; // basic image.

    public Texture(String imageName){
        image = ImageLoader.loadImage(imageName); // initializing basic image by image name.
    }

    // gets image from file by filename.

    public static BufferedImage getImage(String imageName){
        BufferedImage image = ImageLoader.loadImage(imageName);
        int width = image.getWidth();
        int height = image.getHeight();
        return image.getSubimage(0,0, width, height);
    }
}
