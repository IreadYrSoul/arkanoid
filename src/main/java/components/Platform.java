package components;

import game.Game;
import io.Input;
import util.SpriteLoader;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Game component class that extends {@link GameComponent}
 * and represents player handled platform.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */


public class Platform extends GameComponent {
    
    private float speed = 3; // speed of THIS component moving on display.
    private static BufferedImage image; // picture which represents how this game component look.

    public Platform(int x, int y) {
        super(x, y);
        image = SpriteLoader.load("images/platform.png");
    }

    // represent moving of THIS game component.
    // depending of direction (LEFT / RIGHT) platform will
    // moving on left or on right with speed equal 3 pixels per time unit.

    @Override
    public void update(Input input) {
        if (input.getKey(KeyEvent.VK_LEFT)) {
            if (x >= 3) {
                x -= speed;
            }           
        }

        if (input.getKey(KeyEvent.VK_RIGHT)) {
            if (x + image.getWidth() <= Game.WIDTH) {
                x += speed; 
            }
        }
    }

    public Rectangle getBound(){
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
    }    
}