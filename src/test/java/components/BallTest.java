package components;

import io.Input;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import static org.mockito.Mockito.doReturn;


/**
 * @author Alexander Naumov.
 */
@ExtendWith(MockitoExtension.class)
class BallTest {

    private Ball ball;

    @Mock
    private Input input;

    @Test
    void update() throws Exception {
        ball = new Ball();
        doReturn(true).when(input).getKey(KeyEvent.VK_SPACE);
        Field fieldX = Ball.class.getSuperclass().getSuperclass().getDeclaredField("x");
        fieldX.setAccessible(true);
        Field fieldY = Ball.class.getSuperclass().getSuperclass().getDeclaredField("y");
        fieldY.setAccessible(true);

        ball.update(input);

        int endX = (int) fieldX.get(ball);
        int endY = (int) fieldY.get(ball);
        Assertions.assertEquals(102, endX);
        Assertions.assertEquals(105, endY);

        for (int i = 0; i < 140; i++) {
            ball.update(input);
        }
        int startX = (int) fieldX.get(ball);

        ball.update(input);

        endX = (int) fieldX.get(ball);
        Assertions.assertEquals(startX, endX + 2);

        ball.changeDy();

        for (int i = 0; i < 100; i++) {
            ball.update(input);
        }
        int startY = (int) fieldY.get(ball);

        ball.update(input);

        endY = (int) fieldY.get(ball);
        Assertions.assertEquals(startY, endY + 5);
    }

    @Test
    void changeDx() throws Exception {
        ball = new Ball();
        Field fieldDX = Ball.class.getDeclaredField("dX");
        fieldDX.setAccessible(true);

        ball.changeDx();

        int endX = (int) fieldDX.get(ball);
        Assertions.assertEquals(-2, endX);

        ball.changeDx();

        endX = (int) fieldDX.get(ball);
        Assertions.assertEquals(2, endX);
    }

    @Test
    void changeDy() throws Exception {
        ball = new Ball();
        Field fieldDY = Ball.class.getDeclaredField("dY");
        fieldDY.setAccessible(true);
        fieldDY.set(ball, 5);

        ball.changeDy();

        int endY = (int) fieldDY.get(ball);
        Assertions.assertEquals(-5, endY);

        ball.changeDy();

        endY = (int) fieldDY.get(ball);
        Assertions.assertEquals(5, endY);
    }

    @Test
    void testStartPosition() throws Exception {
        ball = new Ball();
        Field fieldX = Ball.class.getSuperclass().getSuperclass().getDeclaredField("x");
        Field fieldY = Ball.class.getSuperclass().getSuperclass().getDeclaredField("y");
        fieldX.setAccessible(true);
        fieldY.setAccessible(true);

        ball.startPosition(150, 300);

        int x = (int) fieldX.get(ball);
        int y = (int) fieldY.get(ball);

        Assertions.assertEquals(178, x);
        Assertions.assertEquals(282, y);
    }

    @Test
    void testGetCircle() {
        ball = new Ball();
        Assertions.assertNotNull(ball.getCircle());
    }
}