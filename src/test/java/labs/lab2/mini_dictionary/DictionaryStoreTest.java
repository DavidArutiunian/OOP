package labs.lab2.mini_dictionary;

import lib.io.FileManager;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DictionaryStoreTest {
    private Dictionary dictionary = new Dictionary();
    private DictionaryStore store = new DictionaryStore();

    public DictionaryStoreTest() throws IOException {
        dictionary.add("hello", "world");
        dictionary.add("hello", "user");
        dictionary.add("hi", "world");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        final var manager = new FileManager("dictionary.tsv");
        Files.delete(Path.of(manager.getFileInstance().getAbsolutePath()));
    }

    @Test
    public void testSaveWorks() throws IOException {
        store.save(dictionary);
        final var manager = new FileManager("dictionary.tsv");
        final var file = manager.getFileInstance();
        assertTrue(file.exists());
        final String expected = "hi\tworld\nhello\tworld\nhello\tuser";
        final String actual = manager.read().strip();
        assertEquals(expected, actual);
    }

    @Test
    public void testLoadWorks() throws IOException {
        store = new DictionaryStore();
        dictionary = store.load();
        final var expectedDict = new Dictionary();
        expectedDict.add("hello", "world");
        expectedDict.add("hello", "user");
        expectedDict.add("hi", "world");
        Map<String, List<String>> expectedMap = new HashMap<>();
        expectedDict.traverse(expectedMap::put);
        Map<String, List<String>> actualMap = new HashMap<>();
        dictionary.traverse(actualMap::put);
        assertThat(expectedMap, is(actualMap));
    }
}
