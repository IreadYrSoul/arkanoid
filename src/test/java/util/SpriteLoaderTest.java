package util;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SpriteLoaderTest {

    @Test
    public void imageLoadTest() {
        BufferedImage image = SpriteLoader.load("images/background.png");
        assertNotNull(image);
    }
}
