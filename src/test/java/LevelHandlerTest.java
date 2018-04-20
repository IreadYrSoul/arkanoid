import graphics.Texture;
import org.junit.Test;
import util.LevelHandler;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class LevelHandlerTest {

    @Test
    public void levelCatalog(){
        String[] levelCatalog = LevelHandler.levelCatalog();
        assertEquals(2, levelCatalog.length);
    }

    @Test
    public void firstLevel(){
        LevelHandler levelHandler = new LevelHandler();
        int[][] doubleArray = levelHandler.firstLevel().getLevelMap();
        assertNotNull(doubleArray);
        for (int[] array: doubleArray){
            assertNotNull(array);
        }
    }

    @Test
    public void imageArray(){
        BufferedImage image =Texture.getImage("background.png");
        assertNotNull(image);


        int[] buffer = getIntArray(image);
        for (int c: buffer) System.out.println(c);
    }

    public static int[] getIntArray(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        int[] result = new int[width * height];
        System.out.println("width:" + width + " height:" + height);

        for (int i = 0; i < result.length; i++) {
            result[i] = image.getRGB(i % width, i / width);
        }

        return result;
    }
}
