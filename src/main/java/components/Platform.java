package components;

import game.Game;
import io.Input;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Game component class that extends {@link DynamicGameComponent}
 * and represents player handled platform.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */
public class Platform extends DynamicGameComponent {

    /**
     * Platform width in pixels.
     */
    private static final int WIDTH = 74;

    /**
     * Platform height in pixels.
     */
    private static final int HEIGHT = 15;

    /**
     * left/right speed moving.
     */
    private static float speed = 2;

    public Platform(int x, int y) {
        super(x, y);
    }

    /**
     * Implementation of {@link DynamicGameComponent#update(Input)}.
     */
    @Override
    public void update(Input input) {
        if (input.getKey(KeyEvent.VK_LEFT)) {
            left();
        }
        if (input.getKey(KeyEvent.VK_RIGHT)) {
            right();
        }
    }

    /**
     * Move left.
     */
    private void left() {
        if (x > 0) {
            x -= speed;
        }
    }

    /**
     * Move right.
     */
    private void right() {
        if ((x + WIDTH) < Game.WIDTH) {
            x += speed;
        }
    }

    /**
     * Get rectangle that represent platform.
     */
    public Rectangle getBound(){
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    /**
     * Implementation of {@link DynamicGameComponent#render(Graphics2D)}.
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }    
}