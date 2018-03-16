package level;

import game.Ball;
import graphics.Texture;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import util.LevelLoader;


public class Level {

    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 20;
    private static int[][] levelMap;
    private static Map<BlockType, Block> map;
    private static Map<Point, String> contactPoints;
    private Ball ball;


    public Level(Ball ball) {
        this.ball = ball;
        map = new HashMap<>();

        map.put(BlockType.BLUE, new Block(Texture.getImage("blue.png"), BlockType.BLUE));
        map.put(BlockType.GREEN, new Block(Texture.getImage("green.png"), BlockType.GREEN));
        map.put(BlockType.YELLOW, new Block(Texture.getImage("yellow.png"), BlockType.YELLOW));
        map.put(BlockType.RED, new Block(Texture.getImage("red.png"), BlockType.RED));
        map.put(BlockType.VIOLET, new Block(Texture.getImage("violet.png"), BlockType.VIOLET));
        map.put(BlockType.EMPTY, new Block(Texture.getImage("grey.png"), BlockType.EMPTY));

        levelMap = LevelLoader.getLevel("levels/1.lvl");

        contactPoints = new HashMap<>();
    }

    public static Map<Point, String> getContactPoints(){

        int height = levelMap.length;
        int width = levelMap[0].length;

        for (int y = 0; y < height; y++) { // y
            for (int x = 0; x < width; x++) { // x
                BlockType type = BlockType.getByNumeric(levelMap[y][x]);

                if (!type.equals(BlockType.EMPTY)) {

                    if (x == 0) { // y = 0
                        if (y == 0) { // y = 0
                            leftOrRight(x + 1, y, "RIGHT");
                            downOrUp(x, y + 1, "DOWN");
                        } else if (y > 0 && y < height - 1) { // y > 0 and y < 7
                            leftOrRight(x + 1, y, "RIGHT");
                            downOrUp(x, y + 1, "DOWN");
                            downOrUp(x, y - 1, "UP");
                        } else { // y = max
                            leftOrRight(x + 1, y, "RIGHT");
                            downOrUp(x, y - 1, "UP");
                        }
                    } else if (x > 0 && x < width - 1) { // x > 0 and x < 29
                        if (y == 0) { // y = 0
                            leftOrRight(x + 1, y, "RIGHT");
                            leftOrRight(x - 1, y, "LEFT");
                            downOrUp(x, y + 1, "DOWN");
                        } else if (y > 0 && y < height - 1) { // y > 0 and y < 29
                            leftOrRight(x - 1, y, "LEFT");
                            leftOrRight(x + 1, y, "RIGHT");
                            downOrUp(x, y + 1, "DOWN");
                            downOrUp(x, y - 1, "UP");
                        } else { // y = max
                            leftOrRight(x + 1, y, "RIGHT");
                            leftOrRight(x - 1, y, "LEFT");
                            downOrUp(x, y - 1, "UP");
                        }
                    } else { // x == 7
                        if (y == 0) { // y = 0
                            leftOrRight(x - 1, y, "LEFT");
                            downOrUp(x, y + 1, "DOWN");
                        } else if (y > 0 && y < height - 1) { // y > 0 and y < 29
                            leftOrRight(x - 1, y, "LEFT");
                            downOrUp(x, y + 1, "DOWN");
                            downOrUp(x, y - 1, "UP");
                        } else { // y = 29
                            leftOrRight(x - 1, y, "LEFT");
                            downOrUp(x, y - 1, "UP");
                        }
                    }
                }
            }
        }

        return contactPoints;
    }


    public static void leftOrRight(int x, int y, String side) {
        int newY = y * BLOCK_HEIGHT;
        int newX = 0;
        if (levelMap[y][x] == 0) {
            if (side.equals("RIGHT")) {
                newX = x * BLOCK_WIDTH;
            } else { // LEFT
                newX = (x + 1) * BLOCK_WIDTH;
            }
            for (int i = newY; i < newY + BLOCK_HEIGHT; i++) {
                contactPoints.put(new Point(newX, i), side);
            }

            int to = newY + BLOCK_HEIGHT;
        }
    }

    public static void downOrUp(int x, int y, String side) {
        int newX = x * BLOCK_WIDTH;
        int newY = 0;
        if (levelMap[y][x] == 0) {
            if (side.equals("UP")){
                newY =  (y + 1) * BLOCK_WIDTH;
            } else { // DOWN
                newY = y * BLOCK_WIDTH;
            }

            for (int i = newX; i < newX + BLOCK_WIDTH; i++) {
                contactPoints.put(new Point(i, newY), side);
            }

            int to = newX + BLOCK_WIDTH;
        }
    }


    public void update() {

        ball.update();

        if (getContactPoints().entrySet().contains(ball)){

            System.out.println("contact");

//            for (Map.Entry<Point, String> point : getContactPoints().entrySet()) {
//                for(Point ballPoint : ball.getContactPoints()){
//
//                    if (point.getKey().equals(ballPoint)){
//
//                        if ( point.getValue().equals("DOWN") && point.getValue().equals("UP") ){
//                            ball.changeVerticallSpeed();
//                        } else {
//                            ball.changeHorizontalSpeed();
//                        }
//
//                    }
//
//                }
//            }

        }

    }

    public void render(Graphics2D g) {
        for (int i = 0; i < levelMap.length; i++) {
            for (int j = 0; j < levelMap[i].length; j++) {
                map.get(BlockType.getByNumeric(levelMap[i][j])).render(g, j * BLOCK_WIDTH, i * BLOCK_HEIGHT);
            }
        }
    }
}
