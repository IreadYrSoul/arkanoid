package model;

import components.Platform;
import io.Input;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.mockito.Mockito.doReturn;

/**
 * @author Alexander Naumov.
 */
@ExtendWith(MockitoExtension.class)
class PlatformTest {

    private static final int WIDTH = 300;

    private static final int pWidth = 74;

    private Platform platform;

    @Mock
    private Input input;

    @Test
    void testRight() throws Exception {
        platform = new Platform(0, 0);
        Method method = Platform.class.getDeclaredMethod("right");
        method.setAccessible(true);
        Field f = platform.getClass().getSuperclass().getSuperclass().getDeclaredField("x");
        f.setAccessible(true);
        for (int i = 0; i < WIDTH; i++) {
            int x = (int) f.get(platform);
            method.invoke(platform);
            if (x + pWidth > WIDTH) {
                Assertions.fail("x + pWidth can't being more than " + WIDTH);
            }
        }
        Assertions.assertFalse((int) f.get(platform) + pWidth > WIDTH);
    }

    @Test
    void testLeft() throws Exception {
        platform = new Platform(0, 0);
        Method method = Platform.class.getDeclaredMethod("left");
        method.setAccessible(true);
        Field f = platform.getClass().getSuperclass().getSuperclass().getDeclaredField("x");
        f.setAccessible(true);
        for (int i = 0; i < WIDTH; i++) {
            int x = (int) f.get(platform);
            method.invoke(platform);
            if (x < 0) {
                Assertions.fail("x can't being less than 0");
            }
        }
        Assertions.assertFalse((int) f.get(platform) < 0);
    }

    @Test
    void updateLeft() throws Exception {
        platform = new Platform(100, 0);
        doReturn(true).when(input).getKey(KeyEvent.VK_LEFT);
        doReturn(false).when(input).getKey(KeyEvent.VK_RIGHT);
        Field f = platform.getClass().getSuperclass().getSuperclass().getDeclaredField("x");
        f.setAccessible(true);
        int startX = (int) f.get(platform);
        platform.update(input);
        int endX = (int) f.get(platform);
        Assertions.assertTrue(startX > endX);
    }

    @Test
    void updateRight() throws Exception {
        platform = new Platform(100, 0);
        doReturn(false).when(input).getKey(KeyEvent.VK_LEFT);
        doReturn(true).when(input).getKey(KeyEvent.VK_RIGHT);
        Field f = platform.getClass().getSuperclass().getSuperclass().getDeclaredField("x");
        f.setAccessible(true);
        int startX = (int) f.get(platform);
        platform.update(input);
        int endX = (int) f.get(platform);
        Assertions.assertTrue(startX < endX);
    }
}
