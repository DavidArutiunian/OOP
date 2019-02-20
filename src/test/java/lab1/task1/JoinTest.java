package lab1.task1;

import io.FileManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinTest {
    @Test
    public void testProcessingWorks() throws IOException {
        final List<File> files = new ArrayList<>(Arrays.asList(
            new FileManager().write("Hello").getFileInstance(),
            new FileManager().write("World").getFileInstance()
        ));
        final var output = new FileManager();
        final var join = new Join(files, output.getFileInstance());
        join.process();
        final var actual = output.read().strip().replaceAll("\\s", "");
        Assert.assertEquals("HelloWorld", actual);
        output.getFileInstance().delete();
        files.forEach(File::delete);
    }
}
