package labs.lab4.shapes.circle;

import labs.lab4.shapes.TestUtils;
import lombok.val;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class CCircleTest {
    @Test
    public void getFillColor() {
        val fillColor = TestUtils.getRandomHex();
        val outlineColor = TestUtils.getRandomHex();
        val radius = TestUtils.getFaker().random().nextDouble();
        val center = TestUtils.getRandomPoint();
        val circle = new CCircle(center, radius, fillColor, outlineColor);
        assertEquals(fillColor, circle.getFillColor());
    }

    @Test
    public void getArea() {
        val fillColor = TestUtils.getRandomHex();
        val outlineColor = TestUtils.getRandomHex();
        val radius = TestUtils.getFaker().random().nextDouble();
        val center = TestUtils.getRandomPoint();
        val circle = new CCircle(center, radius, fillColor, outlineColor);
        val expected = Math.PI * Math.pow(radius, 2);
        assertEquals(expected, circle.getArea(), TestUtils.DELTA);
    }

    @Test
    public void getPerimeter() {
        val fillColor = TestUtils.getRandomHex();
        val outlineColor = TestUtils.getRandomHex();
        val radius = TestUtils.getFaker().random().nextDouble();
        val center = TestUtils.getRandomPoint();
        val circle = new CCircle(center, radius, fillColor, outlineColor);
        val expected = 2 * Math.PI * radius;
        assertEquals(expected, circle.getPerimeter(), TestUtils.DELTA);
    }

    @Test
    public void getOutlineColor() {
        val fillColor = TestUtils.getRandomHex();
        val outlineColor = TestUtils.getRandomHex();
        val radius = TestUtils.getFaker().random().nextDouble();
        val center = TestUtils.getRandomPoint();
        val circle = new CCircle(center, radius, fillColor, outlineColor);
        assertEquals(outlineColor, circle.getOutlineColor());
    }

    @Test
    public void getCenter() {
        val fillColor = TestUtils.getRandomHex();
        val outlineColor = TestUtils.getRandomHex();
        val radius = TestUtils.getFaker().random().nextDouble();
        val center = TestUtils.getRandomPoint();
        val circle = new CCircle(center, radius, fillColor, outlineColor);
        assertThat(circle.getCenter(), is(center));
    }

    @Test
    public void getRadius() {
        val fillColor = TestUtils.getRandomHex();
        val outlineColor = TestUtils.getRandomHex();
        val radius = TestUtils.getFaker().random().nextDouble();
        val center = TestUtils.getRandomPoint();
        val circle = new CCircle(center, radius, fillColor, outlineColor);
        assertEquals(radius, circle.getRadius(), TestUtils.DELTA);
    }
}
