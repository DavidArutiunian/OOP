package labs.lab4.shapes.line_segment;

import labs.lab4.shapes.TestUtils;
import lombok.val;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
}
