package labs.lab1.join;

import lib.io.FileManagerImpl;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class FileManagerTest {
    @Test
    public void testFileCreates() throws IOException {
        final var pathname = UUID.randomUUID().toString();
        final var file = new File(pathname);
        Assert.assertFalse(file.exists());
        final var manager = new FileManagerImpl(pathname);
        Assert.assertTrue(file.exists());
        manager.getFileInstance();
        Assert.assertTrue(file.exists());
        Files.delete(Path.of(file.getAbsolutePath()));
    }

    @Test
    public void testWriteReadWorks() throws IOException {
        final var manager = new FileManagerImpl().write("Hello, World!");
        final var actual = manager.read();
        Assert.assertEquals("Hello, World!", actual.strip());
        Files.delete(Path.of(manager.getFileInstance().getAbsolutePath()));
    }
}
