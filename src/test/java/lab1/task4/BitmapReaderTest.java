package lab1.task4;

import io.FileManager;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BitmapReaderTest {

    @Test
    public void testGetHeader() throws IOException {
        final var manager = new FileManager("./src/test/assets/test.bmp");
        final var reader = new BitmapReader(manager.create());
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
    public void testGetHeaderThrowsException() {
        final var file = new File("./src/test/assets/test.txt");
        assertThrows(UnsupportedEncodingException.class, () -> new BitmapReader(file));
    }

    @Test
    public void testFileEmpty() {
        final var file = new File("test.txt");
        assertThrows(IOException.class, () -> new BitmapReader(file));
    }
}
