package labs.lab4.shapes.point;

import labs.lab4.shapes.TestUtils;
import lombok.val;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PointTest {
    @Test
    public void checkXCorrect() {
        val x = TestUtils.getFaker().random().nextDouble();
        val y = TestUtils.getFaker().random().nextDouble();
        val point = new Point(x, y);
        assertEquals(x, point.x, TestUtils.DELTA);
    }

    @Test
    public void checkYCorrect() {
        val x = TestUtils.getFaker().random().nextDouble();
        val y = TestUtils.getFaker().random().nextDouble();
        val point = new Point(x, y);
        assertEquals(y, point.y, TestUtils.DELTA);
    }
}
