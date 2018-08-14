package util;

import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertNotNull;

public class SpriteLoaderTest {

    @Test
    public void imageLoadTest() {
        BufferedImage image = SpriteLoader.load("images/background.png");
        assertNotNull(image);
    }
}
