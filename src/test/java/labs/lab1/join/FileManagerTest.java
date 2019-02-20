package labs.lab1.join;

import lib.io.FileManager;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileManagerTest {
    @Test
    public void testFileCreates() throws IOException {
        final var pathname = UUID.randomUUID().toString();
        final var file = new File(pathname);
        Assert.assertFalse(file.exists());
        final var manager = new FileManager(pathname);
        Assert.assertTrue(file.exists());
        manager.getFileInstance();
        Assert.assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void testWriteReadWorks() throws IOException {
        final var manager = new FileManager().write("Hello, World!");
        final var actual = manager.read();
        Assert.assertEquals("Hello, World!", actual.strip());
        manager.getFileInstance().delete();
    }
}
