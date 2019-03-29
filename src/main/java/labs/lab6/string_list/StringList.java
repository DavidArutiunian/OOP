package labs.lab6.string_list;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.function.Consumer;

class StringList implements Iterable<StringNode> {
    private int counter = 0;
    @Nullable
    private StringNode first = null;

    void pushBack(String value) {
        if (counter == 0) {
            assert first == null;
            first = new StringNode(value);
        } else {
            val prev = get(counter - 1);
            val node = new StringNode(value);
            node.setPrev(prev);
            assert prev != null;
            prev.setNext(node);
        }
        counter++;
    }

    void pushFront(String value) {
        if (first == null) {
            assert counter == 0;
            first = new StringNode(value);
        } else {
            val node = new StringNode(value);
            first.setPrev(node);
            node.setNext(first);
            first = node;
        }
        counter++;
    }

    void insert(int index, String value) {
        val node = new StringNode(value);
        if (index > counter || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        if (index == 0) {
            val curr = first;
            first = node;
            node.setNext(curr);
            assert curr != null;
            curr.setPrev(node);
        } else {
            val prev = get(index - 1);
            val curr = get(index);
            assert prev != null;
            prev.setNext(node);
            node.setPrev(prev);
            node.setNext(curr);
            if (curr != null) {
                curr.setPrev(node);
            }
        }
    }

    void clear() {
        if (first == null) {
            return;
        }
        var last = get(counter - 1);
        for (int i = counter; i > 0; --i) {
            assert last != null;
            last.setNext(null);
            last = last.getPrev();
        }
        first = null;
        counter = 0;
    }

    @Nullable StringNode get(int index) {
        if (first == null) {
            assert counter == 0;
            throw new NullPointerException("List is empty!");
        }
        var next = first;
        for (int i = 0; i < index; i++) {
            assert next != null;
            next = next.getNext();
        }
        return next;
    }

    void erase(int index) {
        if (index > counter || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        var curr = get(index);
        assert curr != null;
        val prev = curr.getPrev();
        val next = curr.getNext();
        if (prev != null) {
            prev.setNext(next);
        }
        if (next != null) {
            next.setPrev(prev);
        }
        if (index == 0) {
            first = next;
        }
        counter--;
    }

    boolean empty() {
        return counter == 0;
    }

    int size() {
        return counter;
    }

    @NotNull
    @Override
    public Iterator<StringNode> iterator() {
        return new StringListIterator(this);
    }

    @Override
    public void forEach(Consumer<? super StringNode> action) {
        if (first == null) {
            return;
        }
        var it = first;
        for (int i = 0; i < counter; i++) {
            action.accept(it);
            it = first.getNext();
        }
    }
}
