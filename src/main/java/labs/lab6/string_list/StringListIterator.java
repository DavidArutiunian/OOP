package labs.lab6.string_list;

import lombok.val;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StringListIterator implements Iterator<StringNode> {
    private StringNode current;

    StringListIterator(StringList list) {
        if (list.size() == 0) {
            current = null;
        } else {
            current = list.get(0);
        }
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public StringNode next() {
        val next = current;
        if (next == null) {
            throw new NoSuchElementException("No next found!");
        }
        current = current.getNext();
        return next;
    }
}
