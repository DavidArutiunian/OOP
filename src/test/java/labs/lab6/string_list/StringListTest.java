package labs.lab6.string_list;

import lombok.val;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ConstantConditions")
public class StringListTest {
    @Test
    public void middleNodeHasCorrectNextAndPrevValuesOnBack() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        val middle = list.get(1);
        assertThat(middle.getPrev(), is(list.get(0)));
        assertThat(middle.getNext(), is(list.get(2)));
        assertEquals("World", middle.getValue());
    }

    @Test
    public void firstNodeHasNullPrevValueOnBack() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        val first = list.get(0);
        assertNull(first.getPrev());
        assertThat(first.getNext(), is(list.get(1)));
        assertEquals("Hello", first.getValue());
    }

    @Test
    public void nodeIsFirstOnFront() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushFront("Java");
        list.pushBack("World");
        val first = list.get(0);
        assertNull(first.getPrev());
        assert first.getNext() != null;
        assertEquals("Java", first.getValue());
        assertEquals("Hello", first.getNext().getValue());
        assert first.getNext().getNext() != null;
        assertEquals("World", first.getNext().getNext().getValue());
        assertEquals("Hello", list.get(1).getValue());
        assertEquals("World", list.get(2).getValue());
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
    public void insertToMiddleOfList() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        list.insert(1, "C++");
        assertEquals("Hello", list.get(0).getValue());
        assertEquals("C++", list.get(1).getValue());
        assertEquals("World", list.get(2).getValue());
        assertEquals("Java", list.get(3).getValue());
        assertThat(list.get(1).getPrev(), is(list.get(0)));
        assertThat(list.get(1).getNext(), is(list.get(2)));
    }

    @Test
    public void insertToFrontOfList() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        list.insert(0, "C++");
        assertEquals("C++", list.get(0).getValue());
        assertEquals("Hello", list.get(1).getValue());
        assertEquals("World", list.get(2).getValue());
        assertEquals("Java", list.get(3).getValue());
        assertNull(list.get(0).getPrev());
        assertThat(list.get(0).getNext(), is(list.get(1)));
    }

    @Test
    public void insertToBackOfList() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        list.insert(3, "C++");
        assertEquals("Hello", list.get(0).getValue());
        assertEquals("World", list.get(1).getValue());
        assertEquals("Java", list.get(2).getValue());
        assertEquals("C++", list.get(3).getValue());
        assertThat(list.get(3).getPrev(), is(list.get(2)));
        assertNull(list.get(3).getNext());
    }

    @Test
    public void throwsIsIndexOutOfBoundOnInsert() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(4, "C++"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(-1, "C++"));
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
    public void eraseFromMiddle() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        list.erase(1);
        assertEquals("Hello", list.get(0).getValue());
        assertEquals("Java", list.get(1).getValue());
        assertThat(list.get(0).getNext(), is(list.get(1)));
        assertThat(list.get(1).getPrev(), is(list.get(0)));
        assertNull(list.get(1).getNext());
        assertNull(list.get(0).getPrev());
        assertEquals(2, list.size());
    }

    @Test
    public void eraseFromFront() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        list.erase(0);
        assertEquals("World", list.get(0).getValue());
        assertEquals("Java", list.get(1).getValue());
        assertThat(list.get(0).getNext(), is(list.get(1)));
        assertThat(list.get(1).getPrev(), is(list.get(0)));
        assertNull(list.get(1).getNext());
        assertNull(list.get(0).getPrev());
        assertEquals(2, list.size());
    }

    @Test
    public void eraseFromBack() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        list.erase(2);
        assertEquals("Hello", list.get(0).getValue());
        assertEquals("World", list.get(1).getValue());
        assertThat(list.get(0).getNext(), is(list.get(1)));
        assertThat(list.get(1).getPrev(), is(list.get(0)));
        assertNull(list.get(1).getNext());
        assertNull(list.get(0).getPrev());
        assertEquals(2, list.size());
    }

    @Test
    public void listIsIterable() {
        val list = new StringList();
        list.pushBack("Hello");
        list.pushBack("World");
        list.pushBack("Java");
        var counter = 0;
        var it = list.get(0);
        for (final var node : list) {
            assertThat(node, is(it));
            it = it.getNext();
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

}
