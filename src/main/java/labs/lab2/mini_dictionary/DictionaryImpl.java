package labs.lab2.mini_dictionary;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

class DictionaryImpl implements Dictionary {
    private final Map<String, List<String>> dict = new HashMap<>();

    @Override
    public void add(final String key, final String value) {
        if (dict.containsKey(key)) {
            final List<String> list = dict.get(key);
            list.add(value);
        } else {
            final List<String> list = new ArrayList<>();
            list.add(value);
            dict.put(key, list);
        }
    }

    @Override
    @Nullable
    public List<String> get(final String key) {
        return dict.get(key);
    }

    @Override
    public void traverse(BiConsumer<String, List<String>> consumer) {
        dict.forEach(consumer);
    }

    @Override
    public void clear() {
        dict.clear();
    }

    @Override
    public Map<String, List<String>> getMap() {
        return dict;
    }

    @Override
    public boolean contains(String key) {
        return dict.containsKey(key);
    }
}
