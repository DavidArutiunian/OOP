package labs.lab6.string_list;

import lombok.Getter;
import lombok.val;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

class StringList {
    @Getter
    private int counter = 0;
    @Nullable
    private StringNode first = null;
    private List<StringNode> nodes = new LinkedList<>();

    void back(String value) {
        if (counter == 0) {
            assert first == null;
            val node = new StringNode(value);
            first = node;
            nodes.add(node);
        } else {
            val prev = get(counter - 1);
            val node = new StringNode(value);
            node.setPrev(prev);
            prev.setNext(node);
            nodes.add(node);
        }
        counter++;
    }

    void front(String value) {
        if (first == null) {
            assert counter == 0;
            first = new StringNode(value);
        } else {
            val node = new StringNode(value);
            first.setPrev(node);
            node.setNext(first);
            nodes.add(node);
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
}
