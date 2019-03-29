package labs.lab6.string_list;

import lombok.val;
import org.jetbrains.annotations.Nullable;

class StringList {
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

    StringNode get(int index) {
        if (first == null) {
            assert counter == 0;
            throw new NullPointerException("List is empty!");
        }
        StringNode next = first;
        for (int i = 0; i < index; i++) {
            assert next != null;
            next = next.getNext();
        }
        return next;
    }

    boolean empty() {
        return counter == 0;
    }

    int size() {
        return counter;
    }
}
