package labs.lab1.join;

import lib.io.FileManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinTest {
    @Test
    public void joinHelloWorldOutputsHelloWorld() throws IOException {
        final List<File> files = new ArrayList<>(Arrays.asList(
            new FileManager().write("Hello").getFileInstance(),
            new FileManager().write("World").getFileInstance()
        ));
        final var output = new FileManager();
        final var join = new Join(files, output.getFileInstance());
        join.process();
        final var actual = output.read().strip().replaceAll("\\s", "");
        Assert.assertEquals("HelloWorld", actual);
        Files.delete(Path.of(output.getFileInstance().getAbsolutePath()));
        files.forEach(file -> {
            try {
                Files.delete(Path.of(file.getAbsolutePath()));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
    }
}
