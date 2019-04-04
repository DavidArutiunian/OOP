package labs.lab6.string_list;

import lombok.Getter;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

@SuppressWarnings("SameParameterValue")
class StringList implements Iterable<String> {
    private int counter = 0;
    @Nullable
    private StringNode first = null;
    @Nullable
    private StringNode last = null;

    void pushBack(String value) {
        if (counter == 0) {
            assert first == null;
            first = new StringNode(value);
            last = first;
        } else {
            val prev = last;
            last = new StringNode(value);
            last.setPrev(prev);
            assert prev != null;
            prev.setNext(last);
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

    void insert(StringListIterator iterator, String value) throws StringListIteratorException {
        if (iterator.getList() != this) {
            throw new StringListIteratorException("Incorrect iterator!");
        }
        val node = new StringNode(value);
        if (iterator.getCurrent() == first) {
            val curr = first;
            first = node;
            node.setNext(curr);
            assert curr != null;
            curr.setPrev(node);
        } else if (!iterator.hasNext()) {
            val prev = last;
            last = node;
            last.setPrev(prev);
            assert prev != null;
            prev.setNext(last);
        } else {
            val curr = iterator.getCurrent();
            assert curr != null;
            val prev = curr.getPrev();
            assert prev != null;
            prev.setNext(node);
            node.setPrev(prev);
            node.setNext(curr);
            curr.setPrev(node);
        }
        counter++;
    }

    void clear() {
        if (first == null) {
            return;
        }
        for (int i = counter; i > 0; --i) {
            assert last != null;
            last.setNext(null);
            last = last.getPrev();
        }
        first = null;
        counter = 0;
    }

    String get(int index) {
        val next = getStringNode(index);
        return next.getValue();
    }

    private StringNode getStringNode(int index) {
        if (index > counter || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
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
        var curr = getStringNode(index);
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
    public StringListIterator iterator() {
        return new StringListIterator(first, this);
    }

    @Override
    public void forEach(Consumer<? super String> action) {
        if (first == null) {
            return;
        }
        var it = first;
        for (int i = 0; i < counter; i++) {
            assert it != null;
            action.accept(it.getValue());
            it = first.getNext();
        }
    }

    class StringNode {
        @Nullable
        private StringNode prev = null;
        @Nullable
        private StringNode next = null;

        @Getter
        private String value;

        StringNode(String value) {
            this.value = value;
        }

        @Nullable
        private StringNode getNext() {
            return next;
        }

        private void setNext(@Nullable StringNode next) {
            this.next = next;
        }

        @Nullable
        private StringNode getPrev() {
            return prev;
        }

        private void setPrev(@Nullable StringNode prev) {
            this.prev = prev;
        }
    }

    class StringListIterator implements Iterator<String> {
        @Nullable
        private StringNode current;
        private StringList list;

        private StringListIterator(@Nullable StringNode current, StringList list) {
            this.current = current;
            this.list = list;
        }

        @Nullable
        StringNode getCurrent() {
            return current;
        }

        private StringList getList() {
            return list;
        }

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
}
