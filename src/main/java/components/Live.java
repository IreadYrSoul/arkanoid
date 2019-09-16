package components;

import util.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Game component class that extends {@link GameComponent}
 * represent a single life of player.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */
public class Live extends GameComponent {

    /**
     * Image that represents 1 live.
     */
    private static BufferedImage image;

    public Live(int x, int y){
        super(x, y);
        image = SpriteLoader.load("images/live.png");
    }

    /**
     * Get current static image.
     */
    public static BufferedImage getImage() {
        return image;
    }

    /**
     * Implementation of {@link GameComponent#render(Graphics2D)}.
     */
    @Override
    public void render(Graphics2D g){
        g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
    }
}