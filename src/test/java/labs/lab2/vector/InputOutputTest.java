package labs.lab2.vector;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputOutputTest {
    @Test
    public void testReadWorks() throws IOException {
        setSystemInput("1.1 2.2 3.3");
        final String[] arguments = readSystemInput();
        assertEquals("1.1", arguments[0]);
        assertEquals("2.2", arguments[1]);
        assertEquals("3.3", arguments[2]);
    }

    @Test
    public void testReadThrowsIOException() {
        setSystemInput("\n");
        final Scanner in = new Scanner(System.in);
        assertThrows(IOException.class, () -> InputOutput.read(in));
    }

    @Test
    public void testParseWorks() throws IOException {
        setSystemInput("1.1 2.2 3.3");
        final String[] arguments = readSystemInput();
        final List<Float> input = InputOutput.parse(arguments);
        assertEquals(Float.valueOf(1.1f), input.get(0));
        assertEquals(Float.valueOf(2.2f), input.get(1));
        assertEquals(Float.valueOf(3.3f), input.get(2));
    }

    @Test
    public void testParseSorts() throws IOException {
        setSystemInput("1.1 3.3 2.2");
        final String[] arguments = readSystemInput();
        final List<Float> input = InputOutput.parse(arguments);
        assertEquals(Float.valueOf(1.1f), input.get(0));
        assertEquals(Float.valueOf(2.2f), input.get(1));
        assertEquals(Float.valueOf(3.3f), input.get(2));
    }

    @Test
    public void testParseThrowsIOException() throws IOException {
        setSystemInput("1.1 2.2a 3.3");
        final String[] arguments = readSystemInput();
        assertThrows(IOException.class, () -> InputOutput.parse(arguments));
    }

    @Test
    public void testPrintWorks() throws IOException {
        final var mock = new OutputMock();
        setSystemInput("1.1 2.2 3.3");
        final String[] arguments = readSystemInput();
        final List<Float> input = InputOutput.parse(arguments);
        InputOutput.print(input);
        assertEquals("1.100 2.200 3.300", mock.getInput().strip());
        mock.destruct();
    }

    private void setSystemInput(final String input) {
        final var in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    private String[] readSystemInput() throws IOException {
        final Scanner in = new Scanner(System.in);
        return InputOutput.read(in).split(InputOutput.DELIMITER);
    }

    private class OutputMock {
        private final PrintStream original = System.out;
        private final ByteArrayOutputStream mock = new ByteArrayOutputStream();

        OutputMock() {
            System.setOut(new PrintStream(mock));
        }

        String getInput() {
            return mock.toString();
        }

        void destruct() {
            System.setOut(original);
        }
    }
}
