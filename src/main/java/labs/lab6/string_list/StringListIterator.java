package labs.lab6.string_list;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class StringListIterator implements Iterator<String> {
    @Getter
    @Nullable
    private StringNode current;

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public String next() {
        val next = current;
        if (next == null) {
            throw new NoSuchElementException("Iterator doesn't point to next element!");
        }
        current = current.getNext();
        return next.getValue();
    }
}
