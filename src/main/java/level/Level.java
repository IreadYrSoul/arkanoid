package level;

import components.Block;

import java.awt.*;
import java.util.ArrayList;

/**
 * Simple game component class that represents
 * blocks {@link Block} composed as game level.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */
public class Level {

    /**
     * Model that represents game field.
     */
    private static int[][] levelMap;

    /**
     * All blocks on game field.
     */
    private ArrayList<Block> blocks;

    public Level(int[][] array) {
        levelMap = array;
        blocks = new ArrayList<>();
        for (int y = 0; y < levelMap.length; y++) {
            for (int x = 0; x < levelMap[y].length; x++) {
                if (levelMap[y][x] != 0) {
                    blocks.add(new Block(x, y, getByNumeric(levelMap[y][x])));
                }
            }
        }
    }

    /**
     * Get all blocks.
     */
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    /**
     * Render all blocks.
     */
    public void render(Graphics2D g) {
        blocks.forEach(b -> b.render(g));
    }

    /**
     * Remove "killed" block from game field and level model.
     */
    public void removeBlock(Block block) {
        int x = block.getBound().x;
        int y = block.getBound().y;
        for (Block b : blocks) {
           if (b.getBound().x == x && b.getBound().y == y) {
               levelMap[y][x] = 0;
               blocks.remove(b);
               break;
           }
        }
    }

    /**
     * Get appropriated block color by number.
     */
    private Color getByNumeric(int n) {
        switch (n) {
            case 1:
                return Color.blue;
            case 2:
                return Color.green;
            case 3:
                return Color.yellow;
            case 4:
                return Color.red;
            case 5:
                return Color.orange;
            default:
                return Color.white;
        }
    }
}