package labs.lab6.string_list;

import lombok.val;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class StringListTest {
    @Test
    public void middleNodeHasCorrectNextAndPrevValuesOnBack() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        val middle = list.get(1);
        assertEquals("World", middle);
    }

    @Test
    public void firstNodeHasNullPrevValueOnBack() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        val first = list.get(0);
        assertEquals("Hello", first);
    }

    @Test
    public void nodeIsFirstOnFront() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushFront("Java");
        list.pushBack("World");
        val first = list.get(0);
        assertEquals("Java", first);
        assertEquals("Hello", list.get(1));
        assertEquals("World", list.get(2));
    }

    @Test
    public void listIsEmpty() {
        val list = new StringList();
        assertEquals(0, list.size());
        assertTrue(list.empty());
    }

    @Test
    public void listIsNotEmpty() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        assertEquals(2, list.size());
        assertFalse(list.empty());
    }

    @Test
    public void insertToMiddleOfList() throws StringListIteratorException {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        val it = list.iterator();
        it.next();
        list.insert(it, "C++");
        assertEquals("Hello", list.get(0));
        assertEquals("C++", list.get(1));
        assertEquals("World", list.get(2));
        assertEquals("Java", list.get(3));
    }

    @Test
    public void insertToFrontOfList() throws StringListIteratorException {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        list.insert(list.iterator(), "C++");
        assertEquals("C++", list.get(0));
        assertEquals("Hello", list.get(1));
        assertEquals("World", list.get(2));
        assertEquals("Java", list.get(3));
    }

    @Test
    public void insertToBackOfList() throws StringListIteratorException {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        val it = list.iterator();
        it.next();
        it.next();
        list.insert(it, "C++");
        assertEquals("Hello", list.get(0));
        assertEquals("World", list.get(1));
        assertEquals("C++", list.get(2));
        assertEquals("Java", list.get(3));
    }

    @Test
    public void clearList() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.empty());
    }

    @Test
    public void clearEmptyList() {
        val list = new StringList();
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.empty());
    }

    @Test
    public void eraseFromMiddle() throws StringListIteratorException {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        val it = list.iterator();
        it.next();
        list.erase(it);
        assertEquals("Hello", list.get(0));
        assertEquals("Java", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void eraseFromFront() throws StringListIteratorException {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        list.erase(list.iterator());
        assertEquals("World", list.get(0));
        assertEquals("Java", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void eraseFromBack() throws StringListIteratorException {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        val it = list.iterator();
        it.next();
        it.next();
        list.erase(it);
        assertEquals("Hello", list.get(0));
        assertEquals("World", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void listIsIterable() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        var counter = 0;
        var it = list.iterator();
        for (final var string : list) {
            assert it.getCurrent() != null;
            assertThat(string, is(it.getCurrent()));
            it.next();
            counter++;
        }
        assertEquals(list.size(), counter);
    }

    @Test
    @SuppressWarnings("unused")
    public void emptyListIsIterable() {
        val list = new StringList();
        val counter = 0;
        for (final var node : list) {
            assert false;
        }
        assertEquals(list.size(), counter);
    }

    @Test
    public void insertIteratorFromOtherList() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        val other = new StringList();
        other.pushBack("C++");
        assertThrows(StringListIteratorException.class, () -> list.insert(other.iterator(), "C++"));
    }

    @Test
    public void eraseIteratorFromOtherList() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        val other = new StringList();
        other.pushBack("C++");
        assertThrows(StringListIteratorException.class, () -> list.erase(other.iterator()));
    }

    @Test
    public void insertNotBreakingList() throws StringListIteratorException {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        val it = list.iterator();
        it.next();
        it.next();
        it.next();
        list.insert(it, "C++");
        assertEquals(4, list.size());
    }

    @Test
    public void getIndexOutOfBound() throws IndexOutOfBoundsException {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(100));
    }

    @Test
    public void testRemoveAllEmptyStrings() throws StringListIteratorException {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("");
        list.pushBack("World");
        list.pushBack("");
        list.pushBack("Java");
        val it = list.iterator();
        for (String string : list) {
            if (string.isEmpty()) {
                list.erase(it);
            }
            it.next();
        }
        assertEquals(3, list.size());
        assertEquals("Hello", list.get(0));
        assertEquals("World", list.get(1));
        assertEquals("Java", list.get(2));
    }
}
