package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * @author Alexander Naumov.
 */
class GamePointsTest {

    private GamePoints points;

    @Test
    void testIncrement() throws Exception {
        points = new GamePoints();
        Field field = points.getClass().getDeclaredField("total");
        field.setAccessible(true);
        int startValue = (int) field.get(points);

        points.increment();

        int endValue = (int) field.get(points);
        Assertions.assertTrue(endValue > startValue);
        Assertions.assertEquals(startValue + 10, endValue);
    }

    @Test
    void getTotal() {
        points = new GamePoints();

        Assertions.assertEquals("0000", points.getTotal());
        points.increment();
        Assertions.assertEquals("0010", points.getTotal());
        for (int i = 0; i < 50; i++) {
            points.increment();
        }
        Assertions.assertEquals("0510", points.getTotal());
        for (int i = 0; i < 100; i++) {
            points.increment();
        }
        Assertions.assertEquals("1510", points.getTotal());
    }
}