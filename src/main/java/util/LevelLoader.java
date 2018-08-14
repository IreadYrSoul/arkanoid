package util;

import level.Level;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Util class that responsible for loading files(1.lvl)
 * parsing their and creating Level classes {@link Level}
 *
 * @author Alexander Naumov.
 * @version 1.0
 */

public class LevelLoader {

    private static int[][] lvl;
    private static int width;
    private static int currentLevelFile;
    private static final String DIR = "levels";
    private static String[] levelCatalog;

    public LevelLoader() {
        levelCatalog = levelCatalog();
        currentLevelFile = 0;
    }

    /* bring all levels from directory "/resources/levels/" as string array */

    private String[] levelCatalog() {
        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader br = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(DIR)));
        List<String> list = new ArrayList<>();
        br.lines().forEach(list::add);
        /* todo: impossible read "resources/levels" folder when running already packaged jar file!!! */
        return new String[]{"1.lvl", "2.lvl", "3.lvl"};
    }

    /* create level  */

    public Level createLevel() {
        return new Level(getLevel(levelCatalog[currentLevelFile++]));
    }

    /* gets current number of Level (1.lvl, 2.lvl,...etc) */

    public static String getCurrentLevelFile() {
        return Integer.toString(currentLevelFile);
    }

    /* return int[][] where each element represents BlockType */

    private int[][] getLevel(String path) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(DIR + "/" + path)))) {
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

    /* parse String line to int[], where line (or int[]) represent
       line of Blocks on display */

    private static int[] parseToInt(String line) {
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