package labs.lab2.prime_numbers_generator;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PrimesInputOutputTest {
    @Test
    public void testValidateUpperBoundThrowsMax() {
        final int upperBound = 100_000_001;
        assertThrows(IOException.class, () -> InputOutput.validate(upperBound));
    }

    @Test
    public void testValidateUpperBoundThrowsMin() {
        final int upperBound = -1;
        assertThrows(IOException.class, () -> InputOutput.validate(upperBound));
    }

    @Test
    public void testValidateUpperBoundWorks() throws IOException {
        final int upperBound = 99_999_999;
        InputOutput.validate(upperBound);
    }

    @Test
    public void testParseWorks1() throws IOException {
        final String input = "100000000";
        final int actual = InputOutput.parse(input);
        final int expected = 100_000_000;
        assertEquals(actual, expected);
    }

    @Test
    public void testParseWorks2() throws IOException {
        final String input = "-100000000";
        final int actual = InputOutput.parse(input);
        final int expected = -100_000_000;
        assertEquals(actual, expected);
    }

    @Test
    public void testParseThrows() {
        final String input = "100_000_000";
        assertThrows(IOException.class, () -> InputOutput.parse(input));
    }
}
