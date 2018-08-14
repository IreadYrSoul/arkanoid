package components;

import game.Game;
import io.Input;
import util.SpriteLoader;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

/**
 * Simple game component class that extending
 * {@link GameComponent} and represents Ball in game.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */

public class Ball extends GameComponent {

    private static int dY = 5; // speed which ball moving on left / right.
    private static int dX = 2; // speed which ball moving on up /down.
    private static BufferedImage image; // image which represent view ball.
    private boolean stop;
    private Platform platform;

    public Ball(int x, int y, Platform platform){
        super(x, y);
        this.platform = platform;
        image = SpriteLoader.load("images/ball.png");
        startPosition();
        stop = true;
    }

    @Override
    public void update(Input input) {
        if (input.getKey(KeyEvent.VK_SPACE)) {
            stop = false;
        }

        if (!stop) {        // if ball moving.
            if (x <= 0) {
                dX = 2;
            }

            if (x + image.getWidth() >= Game.WIDTH) {
                dX = -2;
            }

            if (y <= 0) {
                dY = 5;
            }

            if (y > Game.HEIGHT){
                stop = true;
            }

        } else {
            startPosition();
        }

        x += dX;
        y += dY;
    }

    public static void changeDx(){
        if (dX > 0) {
            dX = -2;
        } else{
            dX = 2;
        }
    }

    public static void changeDy(){
        if (dY > 0) {
            dY = -5;
        } else{
            dY = 5;
        }
    }

    public Ellipse2D getCircle(){
        return new Ellipse2D.Double(x, y, image.getWidth(), image.getHeight());
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
    }

    public void startPosition() {
        this.x = platform.x + (platform.getBound().width / 2) - image.getWidth() / 2;
        this.y = platform.y - 18;
    }
}