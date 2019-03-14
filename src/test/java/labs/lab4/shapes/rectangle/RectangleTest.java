package labs.lab4.shapes.rectangle;

import labs.lab4.shapes.TestUtils;
import labs.lab4.shapes.canvas.ICanvas;
import labs.lab4.shapes.point.Point;
import lombok.val;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class RectangleTest {
    @Test
    public void getFillColor() {
        val leftTop = TestUtils.getRandomPoint();
        val rightBottom = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val width = TestUtils.getFaker().random().nextDouble();
        val height = TestUtils.getFaker().random().nextDouble();
        val rectangle = new Rectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
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
        val rectangle = new Rectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
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
        val rectangle = new Rectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
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
        val rectangle = new Rectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
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
        val rectangle = new Rectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
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
        val rectangle = new Rectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
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
        val rectangle = new Rectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
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
        val rectangle = new Rectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        assertEquals(height, rectangle.getHeight(), TestUtils.DELTA);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void draw() {
        val mock = mock(ICanvas.class);
        val leftTop = TestUtils.getRandomPoint();
        val rightBottom = TestUtils.getRandomPoint();
        val rightTop = new Point(rightBottom.x, leftTop.y);
        val leftBottom = new Point(leftTop.x, rightBottom.y);
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val width = TestUtils.getFaker().random().nextDouble();
        val height = TestUtils.getFaker().random().nextDouble();
        val rectangle = new Rectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        val startLineCaptor = ArgumentCaptor.forClass(Point.class);
        val endLineCaptor = ArgumentCaptor.forClass(Point.class);
        val lineColorCaptor = ArgumentCaptor.forClass(Integer.class);
        val fillArrayCaptor = ArgumentCaptor.forClass(List.class);
        val fillColorCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(mock).drawLine(startLineCaptor.capture(), endLineCaptor.capture(), lineColorCaptor.capture());
        doNothing().when(mock).fillPolygon(fillArrayCaptor.capture(), fillColorCaptor.capture());
        rectangle.draw(mock);
        {
            val actual = Arrays.asList(leftTop, rightTop, rightBottom, leftBottom);
            val expected = startLineCaptor.getAllValues();
            for (int i = 0; i < expected.size(); i++) {
                assertEquals(expected.get(i).toString(), actual.get(i).toString());
            }
        }
        {
            val actual = Arrays.asList(rightTop, rightBottom, leftBottom, leftTop);
            val expected = endLineCaptor.getAllValues();
            for (int i = 0; i < expected.size(); i++) {
                assertEquals(expected.get(i).toString(), actual.get(i).toString());
            }
        }
        {
            val actual = Arrays.asList(leftTop, rightTop, rightBottom, leftBottom);
            val expected = fillArrayCaptor.getValue();
            for (int i = 0; i < expected.size(); i++) {
                assertEquals(expected.get(i).toString(), actual.get(i).toString());
            }
        }
        assertThat(fillColorCaptor.getValue(), is(fillColor));
        assertThat(lineColorCaptor.getAllValues(), is(Collections.nCopies(4, outlineColor)));
    }
}
