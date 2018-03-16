package game;

import graphics.Texture;
import io.Input;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ball extends GameComponent {

    private int vertical_speed = 3; // speed which ball moving on left / right.
    private int horizontal_speed = 1; // speed which ball moving on up /down.
    private static BufferedImage image; // image which represent view ball.

    public static ArrayList<Point> contactPoints;
    private static int ballX;
    private static int ballY;
    private static int radius;


    public Ball(int x, int y) {
        super(x, y);
        image = Texture.getImage("ball.png");

        ballX = ( x * image.getWidth() ) / 2;
        ballY = ( y * image.getHeight() ) / 2;
        radius = image.getWidth() / 2;
        contactPoints = new ArrayList<>();

        getContactPoints();

        horizontal_speed = 3;
        vertical_speed = 3;
    }

    public static ArrayList<Point> getContactPoints(){
        for (int i = 0; i < 360; i+= 10) {
            int newX = (int) (ballX + radius * Math.cos(i));
            int newY = (int) (ballY + radius * Math.sin(i));
            contactPoints.add(new Point(newX, newY));
        }
        return contactPoints;
    }

    public void update() {
        if (x <= 0){
            horizontal_speed = 1;
        }

        if (x + image.getWidth() >= Game.WIDTH){
            horizontal_speed = -1;
        }

        if (y <= 0){
            vertical_speed = 3;
        }

        if (y + image.getHeight() >= Game.HEIGHT){
            vertical_speed = -3;
        }

        x += horizontal_speed;
        y += vertical_speed;
    }

    public void changeHorizontalSpeed(){
        horizontal_speed = - horizontal_speed;
    }

    public void changeVerticallSpeed(){
        vertical_speed = - vertical_speed;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
    }
}