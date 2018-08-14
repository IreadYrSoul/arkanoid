package components;

import io.Input;
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

public class Live extends GameComponent{

    private static BufferedImage image;

    public Live(int x, int y){
        super(x, y);
        image = SpriteLoader.load("images/live.png");
    }

    public static BufferedImage getImage() {
        return image;
    }

    @Override
    public void update(Input input) {
        // todo
    }

    @Override
    public void render(Graphics2D g){
        g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
    }
}