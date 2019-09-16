package util;

import level.Level;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Util class that responsible for loading files(1.lvl)
 * parsing their and creating Level classes {@link Level}.
 *
 * @author Alexander Naumov.
 * @version 1.0
 */
public class LevelLoader {

    /**
     * Level model.
     */
    private static int[][] lvl;

    /**
     * Level width (in blocks).
     */
    private static int width;

    /**
     * Current level file.
     */
    private static int currLevel;

    /**
     * Levels directory.
     */
    private static final String DIR = "levels";

    /**
     * Levels names.
     */
    private static String[] levelCatalog;

    public LevelLoader() {
        levelCatalog = levelCatalog();
        currLevel = 0;
    }

    /**
     * Bring all levels from directory "/resources/levels/" as string array.
     */
    private String[] levelCatalog() {
        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(classLoader.getResourceAsStream(DIR))));
        return br.lines().toArray(size -> new String[size]);
    }

    /**
     * Create level next level.
     */
    public Level createLevel() {
        return new Level(getLevel(levelCatalog[currLevel++]));
    }

    /**
     * Gets current number of Level (1.lvl, 2.lvl,...etc).
     */
    public String getCurrLevel() {
        return Integer.toString(currLevel);
    }

    /**
     * Get int[][] where each element represents some block.
     */
    private int[][] getLevel(String path) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass()
                        .getClassLoader().getResourceAsStream(DIR + "/" + path))))) {
            List<int[]> list = new ArrayList<>();
            reader.lines().forEach(line -> list.add(parseToInt(line)));
            int height = list.size();
            lvl = new int[height][width];
            for (int i = 0; i < list.size(); i++) {
                int[] array = list.get(i);
                System.arraycopy(array, 0, lvl[i], 0, array.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lvl;
    }

    /**
     * Parse string line to int[], where line (or int[]) represent
     * line of blocks on display.
     */
    private int[] parseToInt(String line) {
        char[] chars = line.toCharArray();
        int[] array = new int[chars.length];
        if (width == 0) {
            width = array.length;
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                array[i] = Character.getNumericValue(chars[i]);
            }
        }
        return array;
    }
}