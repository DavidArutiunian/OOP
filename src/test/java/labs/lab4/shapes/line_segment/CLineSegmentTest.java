package labs.lab4.shapes.line_segment;

import labs.lab4.shapes.TestUtils;
import labs.lab4.shapes.canvas.ICanvas;
import labs.lab4.shapes.point.CPoint;
import lombok.val;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class CLineSegmentTest {
    @Test
    public void getArea() {
        val start = TestUtils.getRandomPoint();
        val end = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val line = new CLineSegment(start, end, outlineColor);
        val expected = 0;
        assertEquals(expected, line.getArea(), TestUtils.DELTA);
    }

    @Test
    public void getPerimeter() {
        val start = TestUtils.getRandomPoint();
        val end = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val line = new CLineSegment(start, end, outlineColor);
        val left = Math.pow(end.x - start.x, 2);
        val right = Math.pow(end.y - start.y, 2);
        val expected = Math.sqrt(left + right);
        assertEquals(expected, line.getPerimeter(), TestUtils.DELTA);
    }

    @Test
    public void getOutlineColor() {
        val start = TestUtils.getRandomPoint();
        val end = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val line = new CLineSegment(start, end, outlineColor);
        assertEquals(outlineColor, line.getOutlineColor());
    }

    @Test
    public void getStartPoint() {
        val start = TestUtils.getRandomPoint();
        val end = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val line = new CLineSegment(start, end, outlineColor);
        assertThat(line.getStartPoint(), is(start));
    }

    @Test
    public void getEngPoint() {
        val start = TestUtils.getRandomPoint();
        val end = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val line = new CLineSegment(start, end, outlineColor);
        assertThat(line.getEndPoint(), is(end));
    }

    @Test
    public void draw() {
        val mock = mock(ICanvas.class);
        val start = TestUtils.getRandomPoint();
        val end = TestUtils.getRandomPoint();
        val outlineColor = TestUtils.getRandomHex();
        val startCaptor = ArgumentCaptor.forClass(CPoint.class);
        val endCaptor = ArgumentCaptor.forClass(CPoint.class);
        val lineColorCaptor = ArgumentCaptor.forClass(Integer.class);
        val line = new CLineSegment(start, end, outlineColor);
        doNothing().when(mock).drawLine(startCaptor.capture(), endCaptor.capture(), lineColorCaptor.capture());
        line.draw(mock);
        assertThat(startCaptor.getValue(), is(start));
        assertThat(endCaptor.getValue(), is(end));
        assertThat(lineColorCaptor.getValue(), is(outlineColor));
    }
}
