package labs.lab4.circle;

import com.github.javafaker.Faker;
import labs.lab4.point.CPoint;
import lombok.val;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class CCircleTest {
    private static final double DELTA = 5e-5;
    private final Faker faker = new Faker();

    @Test
    public void getFillColor() {
        val fillColor = getRandomHex();
        val outlineColor = getRandomHex();
        val radius = faker.random().nextDouble();
        val center = getRandomPoint();
        val circle = new CCircle(center, radius, fillColor, outlineColor);
        assertEquals(fillColor, circle.GetFillColor());
    }

    @Test
    public void getArea() {
        val fillColor = getRandomHex();
        val outlineColor = getRandomHex();
        val radius = faker.random().nextDouble();
        val center = getRandomPoint();
        val circle = new CCircle(center, radius, fillColor, outlineColor);
        val expected = Math.PI * Math.pow(radius, 2);
        assertEquals(expected, circle.GetArea(), DELTA);
    }

    @Test
    public void getPerimeter() {
        val fillColor = getRandomHex();
        val outlineColor = getRandomHex();
        val radius = faker.random().nextDouble();
        val center = getRandomPoint();
        val circle = new CCircle(center, radius, fillColor, outlineColor);
        val expected = 2 * Math.PI * radius;
        assertEquals(expected, circle.GetPerimeter(), DELTA);
    }

    @Test
    public void getOutlineColor() {
        val fillColor = getRandomHex();
        val outlineColor = getRandomHex();
        val radius = faker.random().nextDouble();
        val center = getRandomPoint();
        val circle = new CCircle(center, radius, fillColor, outlineColor);
        assertEquals(outlineColor, circle.GetOutlineColor());
    }

    @Test
    public void getCenter() {
        val fillColor = getRandomHex();
        val outlineColor = getRandomHex();
        val radius = faker.random().nextDouble();
        val center = getRandomPoint();
        val circle = new CCircle(center, radius, fillColor, outlineColor);
        assertThat(circle.GetCenter(), is(center));
    }

    @Test
    public void getRadius() {
        val fillColor = getRandomHex();
        val outlineColor = getRandomHex();
        val radius = faker.random().nextDouble();
        val center = getRandomPoint();
        val circle = new CCircle(center, radius, fillColor, outlineColor);
        assertEquals(radius, circle.GetRadius(), DELTA);
    }

    private CPoint getRandomPoint() {
        return new CPoint(faker.random().nextDouble(), faker.random().nextDouble());
    }

    private int getRandomHex() {
        return faker.number().numberBetween(0x000000, 0xffffff);
    }
}
