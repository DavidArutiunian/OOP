package lab1.task4;

import io.FileManager;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BitmapReaderTest {
    @Test
    public void testGetHeader() throws Exception {
        final var manager = new FileManager("./src/test/assets/test.bmp");
        final var reader = new BitmapReader(manager.getFileInstance());
        final var header = reader.getHeader();
        final var width = 300;
        final var height = 150;
        final var bitsPerPixel = 8;
        final var imageSize = 45000;
        assertEquals(header.getWidth(), width);
        assertEquals(header.getHeight(), height);
        assertEquals(header.getBitsPerPixel(), bitsPerPixel);
        assertEquals(header.getImageSize(), imageSize);
    }

    @Test
    public void testGetHeaderThrowsEOFException() throws IOException {
        final var file = new FileManager().getFileInstance();
        assertThrows(EOFException.class, () -> new BitmapReader(file));
        file.delete();
    }

    @Test
    public void testGetHeaderThrowsUnsupportedEncodingException() throws IOException {
        final var manager = new FileManager().write("Hello, World!");
        final var file = manager.getFileInstance();
        assertThrows(UnsupportedEncodingException.class, () -> new BitmapReader(file));
        file.delete();
    }

    @Test
    public void testFileFileNotFoundException() {
        final var file = new File("not-found.txt");
        assertThrows(FileNotFoundException.class, () -> new BitmapReader(file));
    }
}
