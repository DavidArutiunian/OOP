package labs.lab2.mini_dictionary;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

class Dictionary {
    private final Map<String, List<String>> dict = new HashMap<>();

    public void add(final String word, final String translation) {
        if (dict.containsKey(word)) {
            final List<String> list = dict.get(word);
            list.add(translation);
        } else {
            final List<String> list = new ArrayList<>();
            list.add(translation);
            dict.put(word, list);
        }
    }

    @Nullable
    List<String> get(final String word) {
        return dict.get(word);
    }

    void traverse(BiConsumer<String, List<String>> consumer) {
        dict.forEach(consumer);
    }

    boolean contains(String word) {
        return dict.containsKey(word);
    }
}
