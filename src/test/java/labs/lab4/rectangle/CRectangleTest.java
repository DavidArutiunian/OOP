package labs.lab4.rectangle;

import com.github.javafaker.Faker;
import labs.lab4.factory.ShapeFactory;
import labs.lab4.point.CPoint;
import lombok.val;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CRectangleTest {
    private static final double DELTA = 5e-5;
    private final Faker faker = new Faker();

    @Test
    public void getFillColor() {
        val leftTop = getRandomPoint();
        val rightBottom = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val width = faker.random().nextDouble();
        val height = faker.random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        assertEquals(fillColor, rectangle.getFillColor());
    }

    @Test
    public void getArea() {
        val leftTop = getRandomPoint();
        val rightBottom = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val width = faker.random().nextDouble();
        val height = faker.random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        val expected = width * height;
        assertEquals(expected, rectangle.getArea(), DELTA);
    }

    @Test
    public void getPerimeter() {
        val leftTop = getRandomPoint();
        val rightBottom = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val width = faker.random().nextDouble();
        val height = faker.random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        val expected = 2 * width + 2 * height;
        assertEquals(expected, rectangle.getPerimeter(), DELTA);
    }

    @Test
    public void getOutlineColor() {
        val leftTop = getRandomPoint();
        val rightBottom = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val width = faker.random().nextDouble();
        val height = faker.random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        assertEquals(outlineColor, rectangle.getOutlineColor());
    }

    @Test
    public void getLeftTop() {
        val leftTop = getRandomPoint();
        val rightBottom = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val width = faker.random().nextDouble();
        val height = faker.random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        assertThat(rectangle.getLeftTop(), is(leftTop));
    }

    @Test
    public void getRightBottom() {
        val leftTop = getRandomPoint();
        val rightBottom = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val width = faker.random().nextDouble();
        val height = faker.random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        assertThat(rectangle.getRightBottom(), is(rightBottom));
    }

    @Test
    public void getWidth() {
        val leftTop = getRandomPoint();
        val rightBottom = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val width = faker.random().nextDouble();
        val height = faker.random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        assertEquals(width, rectangle.getWidth(), DELTA);
    }

    @Test
    public void getHeight() {
        val leftTop = getRandomPoint();
        val rightBottom = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val width = faker.random().nextDouble();
        val height = faker.random().nextDouble();
        val rectangle = new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
        assertEquals(height, rectangle.getHeight(), DELTA);
    }

    private CPoint getRandomPoint() {
        return ShapeFactory.createPoint(faker.random().nextDouble(), faker.random().nextDouble());
    }

    private int getRandomHex() {
        return faker.number().numberBetween(0x000000, 0xffffff);
    }
}
