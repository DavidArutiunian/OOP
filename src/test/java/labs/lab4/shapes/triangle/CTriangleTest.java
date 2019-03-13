package labs.lab4.shapes.triangle;

import labs.lab4.shapes.TestUtils;
import labs.lab4.shapes.canvas.ICanvas;
import labs.lab4.shapes.line_segment.CLineSegment;
import labs.lab4.shapes.point.CPoint;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

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
        assertThat(triangle.getVertex1(), is(vertex1));
    }

    @Test
    public void getVertex2() {
        val vertex1 = TestUtils.getRandomPoint();
        val vertex2 = TestUtils.getRandomPoint();
        val vertex3 = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        assertThat(triangle.getVertex2(), is(vertex2));
    }

    @Test
    public void getVertex3() {
        val vertex1 = TestUtils.getRandomPoint();
        val vertex2 = TestUtils.getRandomPoint();
        val vertex3 = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        assertThat(triangle.getVertex3(), is(vertex3));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void draw() {
        val mock = mock(ICanvas.class);
        val vertex1 = TestUtils.getRandomPoint();
        val vertex2 = TestUtils.getRandomPoint();
        val vertex3 = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val fillColor = TestUtils.getRandomHex();
        val triangle = new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
        val startLineCaptor = ArgumentCaptor.forClass(CPoint.class);
        val endLineCaptor = ArgumentCaptor.forClass(CPoint.class);
        val lineColorCaptor = ArgumentCaptor.forClass(Integer.class);
        val fillArrayCaptor = ArgumentCaptor.forClass(List.class);
        val fillColorCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(mock).drawLine(startLineCaptor.capture(), endLineCaptor.capture(), lineColorCaptor.capture());
        doNothing().when(mock).fillPolygon(fillArrayCaptor.capture(), fillColorCaptor.capture());
        triangle.draw(mock);
        {
            val expected = Arrays.asList(vertex1, vertex2, vertex3);
            val actual = startLineCaptor.getAllValues();
            for (int i = 0; i < expected.size(); i++) {
                assertEquals(expected.get(i).toString(), actual.get(i).toString());
            }
        }
        {
            val expected = Arrays.asList(vertex2, vertex3, vertex1);
            val actual = endLineCaptor.getAllValues();
            for (int i = 0; i < expected.size(); i++) {
                assertEquals(expected.get(i).toString(), actual.get(i).toString());
            }
        }
        {
            val expected = Arrays.asList(vertex1, vertex2, vertex3);
            val actual = fillArrayCaptor.getValue();
            for (int i = 0; i < expected.size(); i++) {
                assertEquals(expected.get(i).toString(), actual.get(i).toString());
            }
        }
        assertThat(fillColorCaptor.getValue(), is(fillColor));
        assertThat(lineColorCaptor.getAllValues(), is(Collections.nCopies(3, outlineColor)));
    }
}
