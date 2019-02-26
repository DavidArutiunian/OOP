package labs.lab2.mini_dictionary;

import java.io.IOException;

interface FileDictionaryProvider {
    void load() throws IOException;

    void save() throws IOException;

    Dictionary getDictionary();
}
