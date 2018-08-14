package level;

import util.SpriteLoader;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple game component class that represents
 * blocks {@link Block} composed as game level.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */


public class Level {

    public static final int BLOCK_WIDTH = 50;
    public static final int BLOCK_HEIGHT = 20;
    private static int[][] levelMap;
    private static Map<BlockType, Block> map;
    private ArrayList<Rectangle> rectangleList;


    public Level(int[][] array) {
        map = new HashMap<>();
        map.put(BlockType.BLUE, new Block(SpriteLoader.load("images/blue.png"), BlockType.BLUE));
        map.put(BlockType.GREEN, new Block(SpriteLoader.load("images/green.png"), BlockType.GREEN));
        map.put(BlockType.YELLOW, new Block(SpriteLoader.load("images/yellow.png"), BlockType.YELLOW));
        map.put(BlockType.RED, new Block(SpriteLoader.load("images/red.png"), BlockType.RED));
        map.put(BlockType.VIOLET, new Block(SpriteLoader.load("images/violet.png"), BlockType.VIOLET));
        map.put(BlockType.EMPTY, new Block(SpriteLoader.load("images/grey.png"), BlockType.EMPTY));

        levelMap = array;
        rectangleList = new ArrayList<>();

        updateMap();
    }

    public ArrayList<Rectangle> getRectangleList() {
        return rectangleList;
    }

    public static int[][] getLevelMap() {
        return levelMap;
    }

    public void update() {
        updateMap();
    }

    private void updateMap(){
        rectangleList.clear();
        for (int y = 0; y < levelMap.length; y++) {
            for (int x = 0; x < levelMap[y].length; x++) {
                if (levelMap[y][x] != 0){
                    int newX = x * BLOCK_WIDTH;
                    int newY = y * BLOCK_HEIGHT;

                    rectangleList.add(new Rectangle(newX, newY, BLOCK_WIDTH, BLOCK_HEIGHT));
                }
            }
        }
    }

    public void render(Graphics2D g) {
        for (int y = 0; y < levelMap.length; y++) {
            for (int x = 0; x < levelMap[y].length; x++) {
                map.get(BlockType.getByNumeric(levelMap[y][x])).render(g, x * BLOCK_WIDTH, y * BLOCK_HEIGHT);
            }
        }
    }

    public void removeBlock(int x, int y) {
        levelMap[x][y] = 0;
    }
}