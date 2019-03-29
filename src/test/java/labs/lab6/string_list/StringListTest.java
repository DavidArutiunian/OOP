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
}
