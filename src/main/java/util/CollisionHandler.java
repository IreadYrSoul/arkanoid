package util;

import components.Ball;
import components.Platform;
import level.Level;
import java.awt.*;

/**
 * Util class that response for handling collisions between
 * blocks {@link level.Block} and ball {@link Ball} or
 * platform {@link Platform} and ball.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */

public class CollisionHandler {

    public static boolean blockCollision(Level level, Ball ball){
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
                level.removeBlock((y / Level.BLOCK_HEIGHT), (x / Level.BLOCK_WIDTH));
                return true;
            }
        }
        return false;
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
