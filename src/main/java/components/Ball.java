package components;

import game.Game;
import io.Input;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

/**
 * Simple game component class that extending
 * {@link DynamicGameComponent} and represents ball in game.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */
public class Ball extends DynamicGameComponent {

    /**
     * Radius of ball in pixels.
     */
    private static final int radius = 9;

    /**
     * Speed for up/down moving.
     */
    private static int dY = 5;

    /**
     * Speed for left/right moving.
     */
    private static int dX = 2;

    /**
     * Is ball stopped.
     */
    private static boolean stop;

    public Ball(int x, int y){
        super(x, y);
        stop = true;
    }

    /**
     * Implementation of {@link DynamicGameComponent}.
     */
    @Override
    public void update(Input input) {
        if (input.getKey(KeyEvent.VK_SPACE)) {
            stop = false;
        }
        if (!stop) {
            if (x <= 0) {
                dX = 2;
            }
            if (x + radius >= Game.WIDTH) {
                dX = -2;
            }
            if (y <= 0) {
                dY = 5;
            }
            if (y > Game.HEIGHT){
                stop = true;
            }
            x += dX;
            y += dY;
        }
    }

    /**
     * Switch move direction for X axis.
     */
    public static void changeDx(){
        if (dX > 0) {
            dX = -2;
        } else{
            dX = 2;
        }
    }

    /**
     * Switch move direction for Y axis.
     */
    public static void changeDy(){
        if (dY > 0) {
            dY = -5;
        } else{
            dY = 5;
        }
    }

    /**
     * Get circle that represents ball.
     */
    public Ellipse2D getCircle(){
        return new Ellipse2D.Double(x, y, 2 * radius, 2 * radius);
    }

    /**
     * Implementation of {@link GameComponent}.
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.white);
        g.fillOval(x, y, 2 * radius, 2 * radius);
    }

    /**
     * Set ball on platform.
     */
    public void startPosition(int pX, int pY, int pWidth) {
        this.x = pX + (pWidth / 2);
        this.y = pY - radius + 1;
    }
}