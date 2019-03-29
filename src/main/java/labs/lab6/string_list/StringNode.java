package labs.lab6.string_list;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

class StringNode {
    @Nullable
    @Getter
    @Setter
    private StringNode prev = null;
    @Nullable
    @Getter
    @Setter
    private StringNode next = null;

    @Getter
    private String value;

    StringNode(String value) {
        this.value = value;
    }
}
