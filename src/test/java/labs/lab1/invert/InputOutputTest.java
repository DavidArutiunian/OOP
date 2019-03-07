package labs.lab1.invert;

import lib.io.FileManager;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputOutputTest {
    @Test
    public void testParseWorks() throws IOException {
        final var manager = new FileManager().write("3\t4a\t8\n2.4\t-1b\t11\n7\t-3.2\t0");
        final var file = manager.getFileInstance();
        assertThrows(IOException.class, () -> InputOutput.parse(file, InputOutput.MATRIX_SIZE));
        Files.delete(Path.of(file.getAbsolutePath()));
    }

    @Test
    public void testIncorrectMatrixThrows() throws IOException {
        final var manager = new FileManager().write("3\t4a\t8\t1\n2.4\t-1b\t1\t11\n7\t0\t-3.2\t0");
        final var file = manager.getFileInstance();
        assertThrows(IOException.class, () -> InputOutput.parse(file, InputOutput.MATRIX_SIZE));
        Files.delete(Path.of(file.getAbsolutePath()));
    }
}
