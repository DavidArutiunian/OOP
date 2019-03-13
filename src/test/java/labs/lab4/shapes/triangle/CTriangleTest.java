package labs.lab4.shapes.triangle;

import labs.lab4.shapes.TestUtils;
import labs.lab4.shapes.line_segment.CLineSegment;
import lombok.val;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CTriangleTest {
    @Test
    public void getFillColor() {
        val vertex1 = TestUtils.getRandomPoint();
        val vertex2 = TestUtils.getRandomPoint();
        val vertex3 = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        assertEquals(fillColor, triangle.getFillColor());
    }

    @Test
    public void getArea() {
        val vertex1 = TestUtils.getRandomPoint();
        val vertex2 = TestUtils.getRandomPoint();
        val vertex3 = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        val expected = 0.5 * (vertex1.x * (vertex2.y - vertex3.y) + vertex2.x * (vertex3.y - vertex1.y) + vertex3.x * (vertex1.y - vertex2.y));
        Assert.assertEquals(Math.abs(expected), triangle.getArea(), TestUtils.DELTA);
    }

    @Test
    public void getPerimeter() {
        val vertex1 = TestUtils.getRandomPoint();
        val vertex2 = TestUtils.getRandomPoint();
        val vertex3 = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        val edge1 = new CLineSegment(vertex1, vertex2);
        val edge2 = new CLineSegment(vertex2, vertex3);
        val edge3 = new CLineSegment(vertex3, vertex1);
        val expected = edge1.getPerimeter() + edge2.getPerimeter() + edge3.getPerimeter();
        assertEquals(expected, triangle.getPerimeter(), TestUtils.DELTA);
    }

    @Test
    public void getOutlineColor() {
        val vertex1 = TestUtils.getRandomPoint();
        val vertex2 = TestUtils.getRandomPoint();
        val vertex3 = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        assertEquals(outlineColor, triangle.getOutlineColor());
    }

    @Test
    public void getVertex1() {
        val vertex1 = TestUtils.getRandomPoint();
        val vertex2 = TestUtils.getRandomPoint();
        val vertex3 = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        assertThat(triangle.getVertex1(), CoreMatchers.is(vertex1));
    }

    @Test
    public void getVertex2() {
        val vertex1 = TestUtils.getRandomPoint();
        val vertex2 = TestUtils.getRandomPoint();
        val vertex3 = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        assertThat(triangle.getVertex2(), CoreMatchers.is(vertex2));
    }

    @Test
    public void getVertex3() {
        val vertex1 = TestUtils.getRandomPoint();
        val vertex2 = TestUtils.getRandomPoint();
        val vertex3 = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        assertThat(triangle.getVertex3(), CoreMatchers.is(vertex3));
    }
}
