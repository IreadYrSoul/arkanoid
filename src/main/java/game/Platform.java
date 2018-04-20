package game;

import graphics.Texture;
import io.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Platform extends GameComponent {
    
    private float speed = 3; // speed of THIS component moving on display.
    private BufferedImage image; // picture which represents how this game component look.

    public Platform(int x, int y) {
        super(x, y);
        image = Texture.getImage("platform.png");
    }

    // represent moving of THIS game component.
    // depending of direction (LEFT / RIGHT) platform will
    // moving on left or on right with speed equal 3 pixels per time unit.

    @Override
    public void update(Input input) {
        if (input.getKey(KeyEvent.VK_LEFT)) {
            if (x >= 0) {
                x -= speed;
            }           
        }

        if (input.getKey(KeyEvent.VK_RIGHT)) {
            if (x + image.getWidth() <= Game.WIDTH) {
                x += speed; 
            }
        }
    }

    public int getX(){
        return super.x;
    }

    public int getY(){
        return super.y;
    }

    public Rectangle getBound(){
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    // renders image which represents THIS game component
    // and draw its on display.

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
    }    
}