package util;

import game.Ball;
import game.Platform;
import level.Level;
import java.awt.*;


public class CollisionHandler {

    public static void blockCollision(Level level, Ball ball){
        for (Rectangle block : level.getRectangleList()){
            if (ball.getCircle().intersects(block)){
                boolean minWidth = ball.getCircle().getCenterX() >= block.x;
                boolean maxWidth = ball.getCircle().getCenterX() <= block.x + block.width;
                boolean downIntersect = ball.getCircle().getCenterY() < block.y;
                boolean upIntersect = ball.getCircle().getCenterY() > block.y + block.height;
                if (((minWidth && maxWidth) && downIntersect) || ((minWidth && maxWidth) && upIntersect)){
                    Ball.changeDy();
                } else {
                    Ball.changeDx();
                }
                int x = block.x;
                int y = block.y;
                level.removeBlock((y/20), (x/50));
                break;
            }
        }
    }

    public static void platformCollision(Platform platform, Ball ball){
        if (ball.getCircle().intersects(platform.getBound())){
            boolean maxWidth = ball.getCircle().getMaxX() >= platform.getBound().x;
            boolean minWidth = ball.getCircle().getMinX() <= platform.getBound().x + platform.getBound().width;
            boolean downIntersect = ball.getCircle().getCenterY() < platform.getBound().y;
            boolean upIntersect = ball.getCircle().getCenterY() > platform.getBound().y + platform.getBound().height;
            if (((minWidth && maxWidth) && downIntersect) || ((minWidth && maxWidth) && upIntersect)) {
                Ball.changeDy();
            } else {
                Ball.changeDx();
            }
        }
    }
}
