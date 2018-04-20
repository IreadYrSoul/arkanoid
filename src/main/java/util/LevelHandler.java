package util;

import level.Level;
import java.io.File;

public class LevelHandler {

    private static int currentLevel = 0;
    private static final String folder = "levels/";
    private static String[] levelCatalog;

    public LevelHandler(){
        this.levelCatalog = levelCatalog();
    }

    public static String[] levelCatalog() {
        File[] files = new File(folder).listFiles();
        String[] catalog = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            catalog[i] = files[i].getName();
        }
        return catalog;
    }

    public Level firstLevel(){
        return new Level(LevelLoader.getLevel(folder + levelCatalog[0]));
    }

    public static Level nextLevel(){
        currentLevel++;
        return new Level(LevelLoader.getLevel(folder + levelCatalog[currentLevel]));
    }

    public Level reloadLevel(){
        return null;
    }
}
