package labs.lab2.vector;

import lib.io.OutputMock;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static lib.io.OutputMock.readSystemInput;
import static lib.io.OutputMock.setSystemInput;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputOutputTest {
    @Test
    public void readsIfInputIsCorrect() throws IOException {
        setSystemInput("1.1 2.2 3.3");
        final String[] arguments = readSystemInput();
        assertEquals("1.1", arguments[0]);
        assertEquals("2.2", arguments[1]);
        assertEquals("3.3", arguments[2]);
    }

    @Test
    public void throwsIfInputIsOnlyNewline() {
        setSystemInput("\n");
        final Scanner in = new Scanner(System.in);
        assertThrows(IOException.class, () -> InputOutput.read(in));
    }

    @Test
    public void parsesIfInputIsCorrect() throws IOException {
        setSystemInput("1.1 2.2 3.3");
        final String[] arguments = readSystemInput();
        final List<Float> input = InputOutput.parse(arguments);
        assertEquals(Float.valueOf(1.1f), input.get(0));
        assertEquals(Float.valueOf(2.2f), input.get(1));
        assertEquals(Float.valueOf(3.3f), input.get(2));
    }

    @Test
    public void sortsIfInputIsCorrect() throws IOException {
        setSystemInput("1.1 3.3 2.2");
        final String[] arguments = readSystemInput();
        final List<Float> input = InputOutput.parse(arguments);
        assertEquals(Float.valueOf(1.1f), input.get(0));
        assertEquals(Float.valueOf(2.2f), input.get(1));
        assertEquals(Float.valueOf(3.3f), input.get(2));
    }

    @Test
    public void throwsIfNumberHasChar() throws IOException {
        setSystemInput("1.1 2.2a 3.3");
        final String[] arguments = readSystemInput();
        assertThrows(IOException.class, () -> InputOutput.parse(arguments));
    }

    @Test
    public void printsWithThreeDecimals() throws IOException {
        final var mock = new OutputMock();
        setSystemInput("1.1 2.2 3.3");
        final String[] arguments = readSystemInput();
        final List<Float> input = InputOutput.parse(arguments);
        InputOutput.print(input);
        assertEquals("1.100 2.200 3.300", mock.read().strip());
        mock.destruct();
    }
}
