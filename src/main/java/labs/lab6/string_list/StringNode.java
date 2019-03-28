package labs.lab6.string_list;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

class StringNode {
    @Nullable
    @Getter
    @Setter
    StringNode prev = null;
    @Nullable
    @Getter
    @Setter
    StringNode next = null;

    @Getter
    private String value;

    StringNode(String value) {
        this.value = value;
    }
}
