package labs.lab1.bmpinfo;

import lib.io.FileManagerImpl;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BitmapReaderTest {
    @Test
    public void testGetHeader1() throws Exception {
        final var manager = new FileManagerImpl("./src/test/assets/test.1.bmp");
        final var reader = new BitmapReader(manager.getFileInstance());
        final var header = reader.getHeader();
        final var expectedWidth = 300;
        final var expectedHeight = 150;
        final var expectedBitsPerPixel = 8;
        final var expectedImageSize = 45000;
        assertEquals(expectedWidth, header.getWidth());
        assertEquals(expectedHeight, header.getHeight());
        assertEquals(expectedBitsPerPixel, header.getBitsPerPixel());
        assertEquals(expectedImageSize, header.getImageSize());
    }

    @Test
    public void testGetHeader2() throws Exception {
        final var manager = new FileManagerImpl("./src/test/assets/test.2.bmp");
        final var reader = new BitmapReader(manager.getFileInstance());
        final var header = reader.getHeader();
        final var expectedWidth = 640;
        final var expectedHeight = 480;
        final var expectedBitsPerPixel = 8;
        final var expectedImageSize = 307200;
        assertEquals(expectedWidth, header.getWidth());
        assertEquals(expectedHeight, header.getHeight());
        assertEquals(expectedBitsPerPixel, header.getBitsPerPixel());
        assertEquals(expectedImageSize, header.getImageSize());
    }


    @Test
    public void testGetHeaderThrowsEOFException() throws IOException {
        final var file = new FileManagerImpl().getFileInstance();
        assertThrows(EOFException.class, () -> new BitmapReader(file));
        Files.delete(Path.of(file.getAbsolutePath()));
    }

    @Test
    public void testGetHeaderThrowsUnsupportedEncodingException() throws IOException {
        final var manager = new FileManagerImpl().write("Hello, World!");
        final var file = manager.getFileInstance();
        assertThrows(UnsupportedEncodingException.class, () -> new BitmapReader(file));
        Files.delete(Path.of(file.getAbsolutePath()));
    }

    @Test
    public void testFileFileNotFoundException() {
        final var file = new File("not-found.txt");
        assertThrows(FileNotFoundException.class, () -> new BitmapReader(file));
    }
}
