package labs.lab2.mini_dictionary;

import lib.io.FileManager;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileDictionaryProviderImplTest {
    private final Dictionary dictionary = new DictionaryImpl();
    private final FileDictionaryProvider provider = new FileDictionaryProviderImpl(dictionary);

    public FileDictionaryProviderImplTest() throws IOException {
        dictionary.add("hello", "world");
        dictionary.add("hello", "user");
        dictionary.add("hi", "world");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        final var manager = new FileManager("dictionary.tsv");
        final var path = Path.of(manager.getFileInstance().getAbsolutePath());
        Files.delete(path);
    }

    @Test
    public void testSaveWorks() throws IOException {
        provider.save();
        final var manager = new FileManager("dictionary.tsv");
        final var file = manager.getFileInstance();
        assertTrue(file.exists());
        final String expected = "hi\tworld\nhello\tworld\nhello\tuser";
        final String actual = manager.read().strip();
        assertEquals(expected, actual);
    }

    @Test
    public void testLoadWorks() throws IOException {
        dictionary.clear();
        provider.load();
        final var expected = new DictionaryImpl();
        expected.add("hello", "world");
        expected.add("hello", "user");
        expected.add("hi", "world");
        assertThat(expected.getMap(), is(provider.getDictionary().getMap()));
    }
}
