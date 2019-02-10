package lab1.task1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinTest {
    @Before
    public void setUp() throws IOException {
        {
            final var manager = new FileManager("test1.txt");
            manager.write("Hello");
        }
        {
            final var manager = new FileManager("test2.txt");
            manager.write("World!");
        }
    }

    @After
    public void tearDown() throws IOException {
        {
            final var file = new File("test1.txt");
            file.delete();
        }
        {
            final var file = new File("test2.txt");
            file.delete();
        }
        final var output = new FileManager("output.txt").create();
        output.delete();
    }

    @Test
    public void testProcessingWorks() throws IOException {
        final List<File> files = new ArrayList<>(Arrays.asList(
            new FileManager("test1.txt").create(),
            new FileManager("test2.txt").create()
        ));
        final var output = new FileManager("output.txt");
        final var join = new Join(files, output.create());
        join.process();
        final var actual =  output.read().strip().replaceAll("\\s", "");
        Assert.assertEquals("HelloWorld!", actual);
    }
}
