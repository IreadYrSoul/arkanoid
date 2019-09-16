package components;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Game component class that extend {@link GameComponent}
 * and represents single static block.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */
public class Block extends GameComponent {

    /**
     * block WIDTH in pixels.
     */
    public static final int WIDTH = 50;

    /**
     * block HEIGHT in pixels.
     */
    public static final int HEIGHT = 20;

    /**
     * color of block.
     */
    private Color color;

    public Block(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    /**
     * Get rectangle that represent block.
     */
    public Rectangle getBound(){
        return new Rectangle(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
    }

    /**
     * Implementation of {@link GameComponent#render(Graphics2D)}.
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fill(new Rectangle2D.Float(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT));
        g.setColor(Color.white);
        g.fill(new Rectangle2D.Float((x * WIDTH + 1), (y * HEIGHT + 1), WIDTH - 2, HEIGHT - 2));
    }
}
