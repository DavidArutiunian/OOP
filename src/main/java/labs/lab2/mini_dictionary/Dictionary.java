package labs.lab2.mini_dictionary;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

interface Dictionary {
    void add(final String key, final String value);

    @Nullable
    List<String> get(final String key);

    void traverse(final BiConsumer<String, List<String>> consumer);

    void clear();

    Map<String, List<String>> getMap();

    boolean contains(final String key);
}
