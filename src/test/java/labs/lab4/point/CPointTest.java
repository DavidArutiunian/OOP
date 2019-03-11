package labs.lab4.point;

import com.github.javafaker.Faker;
import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CPointTest {
    private static final double DELTA = 5e-5;
    private final Faker faker = new Faker();

    @Test
    public void getX() {
        val x = faker.random().nextDouble();
        val y = faker.random().nextDouble();
        val point = new CPoint(x, y);
        assertEquals(x, point.x, DELTA);
    }

    @Test
    public void getY() {
        val x = faker.random().nextDouble();
        val y = faker.random().nextDouble();
        val point = new CPoint(x, y);
        assertEquals(y, point.y, DELTA);
    }
}
