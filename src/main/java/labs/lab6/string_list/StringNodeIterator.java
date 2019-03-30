package labs.lab6.string_list;

import lombok.AllArgsConstructor;
import lombok.val;

import java.util.Iterator;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class StringNodeIterator implements Iterator<StringNode> {
    private StringNode current;

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
