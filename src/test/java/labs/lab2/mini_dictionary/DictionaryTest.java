package labs.lab2.mini_dictionary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;


public class DictionaryTest {
    private Dictionary dictionary = new Dictionary();

    public DictionaryTest() {
        dictionary.add("hello", "world");
        dictionary.add("hello", "user");
        dictionary.add("hi", "world");
    }

    @Test
    public void testAddGetWorks() {
        {
            final List<String> expected = new ArrayList<>();
            expected.add("world");
            expected.add("user");
            assertThat(dictionary.get("hello"), is(expected));
        }
        {
            final List<String> expected = new ArrayList<>();
            expected.add("world");
            assertThat(dictionary.get("hi"), is(expected));
        }
    }

    @Test
    public void testTraverseWorks() {
        final var count = new AtomicInteger();
        dictionary.traverse((key, values) -> count.getAndIncrement());
        final int expectedCount = 2;
        assertEquals(expectedCount, count.get());
    }

    @Test
    public void testContainsWorks() {
        assertTrue(dictionary.contains("hello"));
        assertTrue(dictionary.contains("hi"));
        assertFalse(dictionary.contains("test"));
        assertFalse(dictionary.contains("world"));
    }

    @Test
    public void testClearWorks() {
        dictionary = new Dictionary();
        final var count = new AtomicInteger();
        dictionary.traverse((key, values) -> count.getAndIncrement());
        final int expectedCount = 0;
        assertEquals(expectedCount, count.get());
    }
}
