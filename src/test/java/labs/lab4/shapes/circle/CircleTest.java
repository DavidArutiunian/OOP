package labs.lab4.shapes.circle;

import labs.lab4.shapes.TestUtils;
import labs.lab4.shapes.canvas.ICanvas;
import labs.lab4.shapes.point.Point;
import lombok.val;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


public class CircleTest {
    @Test
    public void getFillColor() {
        val fillColor = TestUtils.getRandomHex();
        val outlineColor = TestUtils.getRandomHex();
        val radius = TestUtils.getFaker().random().nextDouble();
        val center = TestUtils.getRandomPoint();
        val circle = new Circle(center, radius, fillColor, outlineColor);
        assertEquals(fillColor, circle.getFillColor());
    }

    @Test
    public void getArea() {
        val fillColor = TestUtils.getRandomHex();
        val outlineColor = TestUtils.getRandomHex();
        val radius = TestUtils.getFaker().random().nextDouble();
        val center = TestUtils.getRandomPoint();
        val circle = new Circle(center, radius, fillColor, outlineColor);
        val expected = Math.PI * Math.pow(radius, 2);
        assertEquals(expected, circle.getArea(), TestUtils.DELTA);
    }

    @Test
    public void getPerimeter() {
        val fillColor = TestUtils.getRandomHex();
        val outlineColor = TestUtils.getRandomHex();
        val radius = TestUtils.getFaker().random().nextDouble();
        val center = TestUtils.getRandomPoint();
        val circle = new Circle(center, radius, fillColor, outlineColor);
        val expected = 2 * Math.PI * radius;
        assertEquals(expected, circle.getPerimeter(), TestUtils.DELTA);
    }

    @Test
    public void getOutlineColor() {
        val fillColor = TestUtils.getRandomHex();
        val outlineColor = TestUtils.getRandomHex();
        val radius = TestUtils.getFaker().random().nextDouble();
        val center = TestUtils.getRandomPoint();
        val circle = new Circle(center, radius, fillColor, outlineColor);
        assertEquals(outlineColor, circle.getOutlineColor());
    }

    @Test
    public void getCenter() {
        val fillColor = TestUtils.getRandomHex();
        val outlineColor = TestUtils.getRandomHex();
        val radius = TestUtils.getFaker().random().nextDouble();
        val center = TestUtils.getRandomPoint();
        val circle = new Circle(center, radius, fillColor, outlineColor);
        assertThat(circle.getCenter(), is(center));
    }

    @Test
    public void getRadius() {
        val fillColor = TestUtils.getRandomHex();
        val outlineColor = TestUtils.getRandomHex();
        val radius = TestUtils.getFaker().random().nextDouble();
        val center = TestUtils.getRandomPoint();
        val circle = new Circle(center, radius, fillColor, outlineColor);
        assertEquals(radius, circle.getRadius(), TestUtils.DELTA);
    }

    @Test
    public void draw() {
        val mock = mock(ICanvas.class);
        val fillColor = TestUtils.getRandomHex();
        val outlineColor = TestUtils.getRandomHex();
        val radius = TestUtils.getFaker().random().nextDouble();
        val center = TestUtils.getRandomPoint();
        val centerCaptor = ArgumentCaptor.forClass(Point.class);
        val radiusCaptor = ArgumentCaptor.forClass(Double.class);
        val lineColorCaptor = ArgumentCaptor.forClass(Integer.class);
        val fillColorCaptor = ArgumentCaptor.forClass(Integer.class);
        val circle = new Circle(center, radius, fillColor, outlineColor);
        doNothing().when(mock).drawCircle(centerCaptor.capture(), radiusCaptor.capture(), lineColorCaptor.capture());
        doNothing().when(mock).fillCircle(centerCaptor.capture(), radiusCaptor.capture(), fillColorCaptor.capture());
        circle.draw(mock);
        val actual = centerCaptor.getAllValues();
        val expected = Arrays.asList(center, center);
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
        }
        assertThat(radiusCaptor.getValue(), is(radius));
        assertThat(fillColorCaptor.getValue(), is(fillColor));
    }
}
