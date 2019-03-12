package labs.lab4.rectangle;

import labs.lab4.TestUtils;
import lombok.val;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CRectangleTest {
    @Test
    public void getFillColor() {
        val leftTop = TestUtils.getRandomPoint();
        val rightBottom = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val width = TestUtils.getFaker().random().nextDouble();
        val height = TestUtils.getFaker().random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        assertEquals(fillColor, rectangle.getFillColor());
    }

    @Test
    public void getArea() {
        val leftTop = TestUtils.getRandomPoint();
        val rightBottom = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val width = TestUtils.getFaker().random().nextDouble();
        val height = TestUtils.getFaker().random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        val expected = width * height;
        assertEquals(expected, rectangle.getArea(), TestUtils.DELTA);
    }

    @Test
    public void getPerimeter() {
        val leftTop = TestUtils.getRandomPoint();
        val rightBottom = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val width = TestUtils.getFaker().random().nextDouble();
        val height = TestUtils.getFaker().random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        val expected = 2 * width + 2 * height;
        assertEquals(expected, rectangle.getPerimeter(), TestUtils.DELTA);
    }

    @Test
    public void getOutlineColor() {
        val leftTop = TestUtils.getRandomPoint();
        val rightBottom = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val width = TestUtils.getFaker().random().nextDouble();
        val height = TestUtils.getFaker().random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        assertEquals(outlineColor, rectangle.getOutlineColor());
    }

    @Test
    public void getLeftTop() {
        val leftTop = TestUtils.getRandomPoint();
        val rightBottom = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val width = TestUtils.getFaker().random().nextDouble();
        val height = TestUtils.getFaker().random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        assertThat(rectangle.getLeftTop(), is(leftTop));
    }

    @Test
    public void getRightBottom() {
        val leftTop = TestUtils.getRandomPoint();
        val rightBottom = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val width = TestUtils.getFaker().random().nextDouble();
        val height = TestUtils.getFaker().random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        assertThat(rectangle.getRightBottom(), is(rightBottom));
    }

    @Test
    public void getWidth() {
        val leftTop = TestUtils.getRandomPoint();
        val rightBottom = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val width = TestUtils.getFaker().random().nextDouble();
        val height = TestUtils.getFaker().random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        assertEquals(width, rectangle.getWidth(), TestUtils.DELTA);
    }

    @Test
    public void getHeight() {
        val leftTop = TestUtils.getRandomPoint();
        val rightBottom = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val width = TestUtils.getFaker().random().nextDouble();
        val height = TestUtils.getFaker().random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        assertEquals(height, rectangle.getHeight(), TestUtils.DELTA);
    }
}
