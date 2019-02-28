package labs.lab2.mini_dictionary;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

class Dictionary {
    private final Map<String, List<String>> dictionary = new HashMap<>();

    public void add(final String word, final String translation) {
        if (dictionary.containsKey(word)) {
            final List<String> translations = dictionary.get(word);
            translations.add(translation);
        } else {
            final List<String> translations = new ArrayList<>();
            translations.add(translation);
            dictionary.put(word, translations);
        }
    }

    @Nullable
    List<String> get(final String word) {
        return dictionary.get(word);
    }

    void traverse(BiConsumer<String, List<String>> consumer) {
        dictionary.forEach(consumer);
    }

    boolean contains(String word) {
        return dictionary.containsKey(word);
    }
}
