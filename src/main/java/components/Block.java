package components;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

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

    Block(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fill(new RoundRectangle2D.Float(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT, 9.0F, 9.0F));
        g.setColor(color);
        g.fill(new RoundRectangle2D.Float((x + 1), (y + 1), WIDTH - 2, HEIGHT - 2, 8.0F, 8.0F));
    }
}
