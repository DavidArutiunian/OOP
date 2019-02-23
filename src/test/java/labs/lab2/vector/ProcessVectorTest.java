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
    public void testGetAverage1() {
        final List<Float> input = Arrays.asList(6.7f, 3.8f, 68.4f, 127.f, -1.28f);
        final var pv = new ProcessVector(input);
        assertEquals(40.924f, pv.getAverage(), DELTA);
    }

    @Test
    public void testGetAverage2() {
        final List<Float> input = Arrays.asList(0.f, 0.f, 0.f, 0.f, 0.f);
        final var pv = new ProcessVector(input);
        assertEquals(0.f, pv.getAverage(), DELTA);
    }

    @Test
    public void testGetAverage3() {
        final List<Float> input = Arrays.asList(Float.MAX_VALUE, Float.MAX_VALUE);
        final var pv = new ProcessVector(input);
        assertEquals(Float.POSITIVE_INFINITY, pv.getAverage(), DELTA);
    }

    @Test
    public void testGetAverage4() {
        final List<Float> input = Arrays.asList(Float.MAX_VALUE, Float.MIN_VALUE);
        final var pv = new ProcessVector(input);
        assertEquals(1.7014117e38f, pv.getAverage(), DELTA);
    }

    @Test
    public void testProcessingWorks1() {
        final List<Float> input = Arrays.asList(6.7f, 3.8f, 68.4f, 127.f, -1.28f);
        final var pv = new ProcessVector(input);
        final List<Float> actual = pv.call();
        assertThat(actual, is(input));
    }

    @Test
    public void testProcessingWorks2() {
        final List<Float> input = Arrays.asList(6.7f, 3.8f, 68.4f, 127.f, -1.28f);
        final var pv = new ProcessVector(input);
        final List<Float> actual = pv.call(pv.getAverage());
        final List<Float> expected = Arrays.asList(47.624f, 44.724f, 109.324005f, 167.924f, 39.644f);
        assertThat(actual, is(expected));
    }

    @Test
    public void testProcessingWorks3() {
        final List<Float> input = Arrays.asList(6.7f, 3.8f, 68.4f, 127.f, -1.28f);
        final var pv = new ProcessVector(input);
        final List<Float> actual = pv.call(Float.MAX_VALUE);
        final List<Float> expected = Arrays.asList(3.4028235e38f, 3.4028235e38f, 3.4028235e38f, 3.4028235e38f, 3.4028235e38f);
        assertThat(actual, is(expected));
    }

    @Test
    public void testProcessingWorks4() {
        final List<Float> input = Arrays.asList(6.7f, 3.8f, 68.4f, 127.f, -1.28f);
        final var pv = new ProcessVector(input);
        final List<Float> actual = pv.call(Float.MIN_VALUE);
        assertThat(actual, is(input));
    }
}
