package components;

import io.Input;
import util.SpriteLoader;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Simple game component class that represents Background,
 * and extending {@link GameComponent}.
 *
 * @author Alenander Naumov
 * @version 1.0
 */

public class Background extends GameComponent{

    private static BufferedImage image;

    public Background(int x, int y) {
        super(x, y);
        image = SpriteLoader.load("images/background.png");
    }

    @Override
    public void update(Input input) {
        // todo
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
    }
}