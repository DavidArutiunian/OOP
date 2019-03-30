package labs.lab2.mini_dictionary;

import java.io.IOException;

interface ISavableDictionary {
    void save(Dictionary dictionary) throws IOException;
}
