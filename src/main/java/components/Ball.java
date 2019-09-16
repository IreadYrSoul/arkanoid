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
    private static final int RADIUS = 9;

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

    private static boolean onPlatform;

    public Ball() {
        super(100, 100);
        stop = true;
        onPlatform = true;
    }

    /**
     * Implementation of {@link DynamicGameComponent}.
     */
    @Override
    public void update(Input input) {
        if (input.getKey(KeyEvent.VK_SPACE)) {
            stop = false;
            onPlatform = false;
        }
        if (!stop) {
            if (x <= 0) {
                dX = 2;
            }
            if (x + RADIUS >= Game.WIDTH) {
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
        } else if (onPlatform){
            if (input.getKey(KeyEvent.VK_LEFT)) {
                left();
            }
            if (input.getKey(KeyEvent.VK_RIGHT)) {
                right();
            }
        }
    }

    /**
     * Switch move direction for X axis.
     */
    public void changeDx() {
        if (dX > 0) {
            dX = -2;
        } else{
            dX = 2;
        }
    }

    /**
     * Switch move direction for Y axis.
     */
    public void changeDy() {
        if (dY > 0) {
            dY = -5;
        } else{
            dY = 5;
        }
    }

    /**
     * Move ball to left.
     */
    private void left() {
        if (x > Platform.WIDTH / 2 - 9) {
            x -= 2;
        }
    }

    /**
     * Move ball to right.
     */
    private void right() {
        if (x < Game.WIDTH - (Platform.WIDTH / 2 + 9)) {
            x += 2;
        }
    }

    /**
     * Get circle that represents ball.
     */
    public Ellipse2D getCircle(){
        return new Ellipse2D.Double(x, y, 2 * RADIUS, 2 * RADIUS);
    }

    /**
     * Implementation of {@link GameComponent}.
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fillOval(x, y, 2 * RADIUS, 2 * RADIUS);
        g.setColor(Color.white);
        g.fillOval(x + 1, y + 1, (2 * RADIUS) - 2, (2 * RADIUS) - 2);
    }

    /**
     * Set ball on platform.
     */
    public void startPosition(int pX, int pY) {
        this.x = pX + (Platform.WIDTH / 2) - RADIUS;
        this.y = pY - 2 * RADIUS;
    }
}