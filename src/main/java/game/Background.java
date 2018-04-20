package game;

import graphics.Texture;
import io.Input;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends GameComponent{

    private static BufferedImage image;

    public Background(int x, int y) {
        super(x, y);
        image = Texture.getImage("background.png");
    }

    @Override
    public void update(Input input) {

    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
    }
}
