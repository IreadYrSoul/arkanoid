package util;

import game.Game;
import level.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * @author Alexander Naumov
 * @version 1.0
 */

class LevelLoaderTest {

    private LevelLoader loader;


    @Test
    void getLevelTest() {
        loader = new LevelLoader();
        String path = "";
    }

    @Test
    void testParseToInt() throws Exception {
        loader = new LevelLoader();
        String string = "1110100";
        Method method = LevelLoader.class.getDeclaredMethod("parseToInt", String.class);
        method.setAccessible(true);

        int[] result = (int[]) method.invoke(loader, string);

        int[] actual = {1, 1, 1, 0, 1, 0, 0};
        Assertions.assertArrayEquals(actual, result);
    }

    @Test
    void testLevelCatalog() throws Exception {
        loader = new LevelLoader();
        Method method = LevelLoader.class.getDeclaredMethod("levelCatalog");
        method.setAccessible(true);

        String[] strings = (String[])method.invoke(loader);

        String[] array = {"1.lvl", "2.lvl", "3.lvl"};
        Assertions.assertArrayEquals(array, strings);
    }

    @Test
    void testGetCurrentLevel() {
        loader = new LevelLoader();

        String result = loader.getCurrLevel();

        Assertions.assertEquals("0", result);
    }

    @Test
    void testCreateLevel() {
        loader = new LevelLoader();
        String curLevel = loader.getCurrLevel();

        loader.createLevel();

        String nextLevel = loader.getCurrLevel();
        Assertions.assertEquals("0", curLevel);
        Assertions.assertEquals("1", nextLevel);

        loader.createLevel();

        nextLevel = loader.getCurrLevel();
        Assertions.assertEquals("2", nextLevel);
    }

    @Test
    void testGetLevel() throws Exception {
        loader = new LevelLoader();
        Method method = LevelLoader.class.getDeclaredMethod("getLevel", String.class);
        method.setAccessible(true);
        int[][] actual = new int[30][6];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 6; j++) {
                actual[i][j] = 0;
                if (i == 4) {
                    actual[i][j] = 1;
                }
                if (i == 5) {
                    actual[i][j] = 2;
                }
                if (i == 6) {
                    actual[i][j] = 3;
                }
                if (i == 7) {
                    actual[i][j] = 4;
                }
                if (i == 8) {
                    actual[i][j] = 5;
                }
            }
        }

        int[][] result = (int[][]) method.invoke(loader, "1.lvl");

        Assertions.assertArrayEquals(actual, result);
    }
}