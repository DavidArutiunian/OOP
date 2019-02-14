package lab1.task1;

import io.FileManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileManagerTest {
    @Before
    public void setUp() {
        final var file = new File("test.txt");
        file.delete();
    }

    @After
    public void tearDown() {
        final var file = new File("test.txt");
        file.delete();
    }

    @Test
    public void testFileCreates() throws IOException {
        final var file = new File("test.txt");
        Assert.assertFalse(file.exists());
        final var manager = new FileManager("test.txt");
        Assert.assertFalse(file.exists());
        manager.create();
        Assert.assertTrue(file.exists());
    }

    @Test
    public void testWriteReadWorks() throws IOException {
        final var manager = new FileManager("test.txt");
        manager.create();
        manager.write("Hello, World!");
        final var actual = manager.read();
        Assert.assertEquals("Hello, World!", actual.strip());
    }
}
