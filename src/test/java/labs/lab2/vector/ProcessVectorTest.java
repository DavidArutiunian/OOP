package labs.lab2.vector;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class ProcessVectorTest {
    private static float DELTA = 0.005f;

    @Test
    public void averageOfFloats() {
        final List<Float> input = Arrays.asList(6.7f, 3.8f, 68.4f, 127.f, -1.28f);
        final var pv = new ProcessVector(input);
        assertEquals(40.924f, pv.getAverage(), DELTA);
    }

    @Test
    public void averageOfZerosIsZero() {
        final List<Float> input = Arrays.asList(0.f, 0.f, 0.f, 0.f, 0.f);
        final var pv = new ProcessVector(input);
        assertEquals(0.f, pv.getAverage(), DELTA);
    }

    @Test
    public void averageOfMaxValuesIsInfinity() {
        final List<Float> input = Arrays.asList(Float.MAX_VALUE, Float.MAX_VALUE);
        final var pv = new ProcessVector(input);
        assertEquals(Float.POSITIVE_INFINITY, pv.getAverage(), DELTA);
    }

    @Test
    public void averageOfMaxAndMinValues() {
        final List<Float> input = Arrays.asList(Float.MAX_VALUE, Float.MIN_VALUE);
        final var pv = new ProcessVector(input);
        assertEquals(1.7014117e38f, pv.getAverage(), DELTA);
    }

    @Test
    public void vectorProcessingWorksCorrectly() {
        final List<Float> input = Arrays.asList(6.7f, 3.8f, 68.4f, 127.f, -1.28f);
        final var pv = new ProcessVector(input);
        final List<Float> actual = pv.call();
        assertThat(actual, is(input));
    }

    @Test
    public void averageOfVectorProcessingWorksCorrectly() {
        final List<Float> input = Arrays.asList(6.7f, 3.8f, 68.4f, 127.f, -1.28f);
        final var pv = new ProcessVector(input);
        final List<Float> actual = pv.call(pv.getAverage());
        final List<Float> expected = Arrays.asList(47.624f, 44.724f, 109.324005f, 167.924f, 39.644f);
        assertThat(actual, is(expected));
    }

    @Test
    public void averageOfVectorWithMaxValueWorksCorrectly() {
        final List<Float> input = Arrays.asList(6.7f, 3.8f, 68.4f, 127.f, -1.28f);
        final var pv = new ProcessVector(input);
        final List<Float> actual = pv.call(Float.MAX_VALUE);
        final List<Float> expected = Arrays.asList(3.4028235e38f, 3.4028235e38f, 3.4028235e38f, 3.4028235e38f, 3.4028235e38f);
        assertThat(actual, is(expected));
    }

    @Test
    public void averageOfVectorWithMinValueWorksCorrectly() {
        final List<Float> input = Arrays.asList(6.7f, 3.8f, 68.4f, 127.f, -1.28f);
        final var pv = new ProcessVector(input);
        final List<Float> actual = pv.call(Float.MIN_VALUE);
        assertThat(actual, is(input));
    }
}
