package util;

import game.Game;
import level.Block;
import level.Level;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Alexander Naumov
 * @version 1.0
 */

public class LevelLoaderTest {

    private static final int BLOCK_HEIGHT = 20;
    private static final int BLOCK_WIDTH = 50;


    @Test
    public void getLevelTest() {
        Level lvl = new LevelLoader().createLevel();
        int[][] map = lvl.getLevelMap();
        assertNotNull(map);
        assertEquals(map.length, Game.HEIGHT / BLOCK_HEIGHT);
        assertEquals(map[0].length, Game.WIDTH / BLOCK_WIDTH);
    }

}