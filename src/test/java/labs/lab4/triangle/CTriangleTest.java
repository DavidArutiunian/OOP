package labs.lab4.triangle;

import com.github.javafaker.Faker;
import labs.lab4.line_segment.CLineSegment;
import labs.lab4.point.CPoint;
import lombok.val;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CTriangleTest {
    private static final double DELTA = 5e-5;
    private final Faker faker = new Faker();

    @Test
    public void getFillColor() {
        val vertex1 = getRandomPoint();
        val vertex2 = getRandomPoint();
        val vertex3 = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        assertEquals(fillColor, triangle.GetFillColor());
    }

    @Test
    public void getArea() {
        val vertex1 = getRandomPoint();
        val vertex2 = getRandomPoint();
        val vertex3 = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        val expected = 0.5 * (vertex1.x * (vertex2.y - vertex3.y) + vertex2.x * (vertex3.y - vertex1.y) + vertex3.x * (vertex1.y - vertex2.y));
        assertEquals(expected, triangle.GetArea(), DELTA);
    }

    @Test
    public void getPerimeter() {
        val vertex1 = getRandomPoint();
        val vertex2 = getRandomPoint();
        val vertex3 = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        val edge1 = new CLineSegment(vertex1, vertex2);
        val edge2 = new CLineSegment(vertex2, vertex3);
        val edge3 = new CLineSegment(vertex3, vertex1);
        val expected = edge1.GetPerimeter() + edge2.GetPerimeter() + edge3.GetPerimeter();
        assertEquals(expected, triangle.GetPerimeter(), DELTA);
    }

    @Test
    public void getOutlineColor() {
        val vertex1 = getRandomPoint();
        val vertex2 = getRandomPoint();
        val vertex3 = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        assertEquals(outlineColor, triangle.GetOutlineColor());
    }

    @Test
    public void getVertex1() {
        val vertex1 = getRandomPoint();
        val vertex2 = getRandomPoint();
        val vertex3 = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        assertThat(triangle.GetVertex1(), is(vertex1));
    }

    @Test
    public void getVertex2() {
        val vertex1 = getRandomPoint();
        val vertex2 = getRandomPoint();
        val vertex3 = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        assertThat(triangle.GetVertex2(), is(vertex2));
    }

    @Test
    public void getVertex3() {
        val vertex1 = getRandomPoint();
        val vertex2 = getRandomPoint();
        val vertex3 = getRandomPoint();
        val outlineColor = getRandomHex();
        val fillColor = getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        assertThat(triangle.GetVertex3(), is(vertex3));
    }

    private CPoint getRandomPoint() {
        return new CPoint(faker.random().nextDouble(), faker.random().nextDouble());
    }

    private int getRandomHex() {
        return faker.number().numberBetween(0x000000, 0xffffff);
    }
}
