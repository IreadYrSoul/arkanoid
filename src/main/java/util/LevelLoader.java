package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LevelLoader {

    private static int[][] level;
    private static int width;
    private static int height;


    public static int[][] getLevel(String path){
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))){
            String line;
            List<int[]> list = new ArrayList<>();
            while ((line = reader.readLine()) != null){
                list.add(parseToInt(line));
            }
            height =  list.size();
            level = new int[height][width];
            for (int i = 0; i < list.size(); i++) {
                int[] array = list.get(i);
                for (int j = 0; j < array.length; j++) {
                    level[i][j] = array[j];
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return level;
    }

    public static int[] parseToInt(String line){
        char[] chars = line.toCharArray();
        int[] array = new int[chars.length];
        if (width  == 0){
            width = array.length;
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' '){
                array[i] = Character.getNumericValue(chars[i]);
            }
        }
        return array;
    }
}