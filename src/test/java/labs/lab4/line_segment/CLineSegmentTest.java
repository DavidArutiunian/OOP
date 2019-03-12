package labs.lab4.line_segment;

import com.github.javafaker.Faker;
import labs.lab4.factory.ShapeFactory;
import labs.lab4.point.CPoint;
import lombok.val;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CLineSegmentTest {
    private static final double DELTA = 5e-5;
    private final Faker faker = new Faker();

    @Test
    public void getArea() {
        val start = getRandomPoint();
        val end = getRandomPoint();
        val outlineColor = getRandomHex();
        val line = new CLineSegment(start, end, outlineColor);
        val expected = 0;
        assertEquals(expected, line.getArea(), DELTA);
    }

    @Test
    public void getPerimeter() {
        val start = getRandomPoint();
        val end = getRandomPoint();
        val outlineColor = getRandomHex();
        val line = new CLineSegment(start, end, outlineColor);
        val left = Math.pow(end.x - start.x, 2);
        val right = Math.pow(end.y - start.y, 2);
        val expected = Math.sqrt(left + right);
        assertEquals(expected, line.getPerimeter(), DELTA);
    }

    @Test
    public void getOutlineColor() {
        val start = getRandomPoint();
        val end = getRandomPoint();
        val outlineColor = getRandomHex();
        val line = new CLineSegment(start, end, outlineColor);
        assertEquals(outlineColor, line.getOutlineColor());
    }

    @Test
    public void getStartPoint() {
        val start = getRandomPoint();
        val end = getRandomPoint();
        val outlineColor = getRandomHex();
        val line = new CLineSegment(start, end, outlineColor);
        assertThat(line.getStartPoint(), is(start));
    }

    @Test
    public void getEngPoint() {
        val start = getRandomPoint();
        val end = getRandomPoint();
        val outlineColor = getRandomHex();
        val line = new CLineSegment(start, end, outlineColor);
        assertThat(line.getEndPoint(), is(end));
    }

    private CPoint getRandomPoint() {
        return ShapeFactory.createPoint(faker.random().nextDouble(), faker.random().nextDouble());
    }

    private int getRandomHex() {
        return faker.number().numberBetween(0x000000, 0xffffff);
    }
}
