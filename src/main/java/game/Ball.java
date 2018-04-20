package game;

import graphics.Texture;
import io.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;


public class Ball extends GameComponent {

    private static int dY = 5; // speed which ball moving on left / right.
    private static int dX = 2; // speed which ball moving on up /down.
    private static BufferedImage image; // image which represent view ball.
    private boolean stop;
    private Platform platform;

    public Ball(int x, int y, Platform platform){
        super(x, y);
        image = Texture.getImage("ball.png");
        stop = true;
        this.platform = platform;
    }

    @Override
    public void update(Input input) {
        if (input.getKey(KeyEvent.VK_SPACE)) {
            stop = false;
        }

        if (!stop) {
            if (x <= 20) {
                dX = 2;
            }

            if (x + image.getWidth() >= Game.WIDTH - 20) {
                dX = -2;
            }

            if (y <= 20) {
                dY = 5;
            }

            if (y + image.getHeight() >= Game.HEIGHT) {
                dY = -5;
            }

            x += dX;
            y += dY;
        } else {
            x = platform.x + (platform.getBound().width / 2) - 8;
            y = platform.y - 16;
        }
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
        stop = true;
        x = platform.x + (platform.getBound().width / 2) - 8;
        y = platform.y - 16;
    }
}